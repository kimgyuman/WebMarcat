package marcat.board.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.aws.AwsS3Service;
import marcat.board.dto.*;
import marcat.board.service.BoardService;

import marcat.board.vo.*;
import marcat.goods.dto.*;
import marcat.members.dto.JusoDTO;
import marcat.members.service.MemberService;
import marcat.members.vo.Member;
import marcat.security.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;
    private final AwsS3Service awsS3Service;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Value("#{auth['jwt_secret']}")
    private String secretKey;

    //게시판 메인
    @GetMapping("/board")
    public String boardMain() {
        return "board/boardMain";
    }

    //  게시글 상세보기
    @GetMapping("/board/content")
    public String boardContent(@RequestParam("id") long id, Model model, Principal principal
            , HttpServletRequest request, HttpServletResponse response) {

        String principalId = "";
        if (principal != null) {
            principalId = principal.getName();
            model.addAttribute("loginedId", true);
            model.addAttribute("loginedIdString", principalId);
        } else {
            model.addAttribute("loginedId", false);
            model.addAttribute("loginedIdString", false);
        }
        List<Board> searched = boardService.findById(id, principalId);
        List<BoardImages> imageList = boardService.findImagesById(id);
        List<String> imageName = new ArrayList<>(); // 객체에서 이름만 가져오기
        if (!searched.isEmpty()) {
            Board board = searched.get(0); // 데이터가 중복으로 나타날 가능성이 있어 하나만 가져와 객체로 만들어주기
            Object s = board.getBoardReport().getBoardId().toString();
            boolean wishResult = false;
            boolean report = false;
            if (principal != null) {

                wishResult = board.getBoardWishList().getId() != null;

                report = board.getBoardReport().getId() != null;
            }

            for (int i = 0; i < imageList.size(); i++) { // 파일 이름 리스트에 넣기
                String savedFileName = imageList.get(i).getSavedFileName();
                imageName.add(savedFileName);
            }

            BoardContentsDTO boardContentsDTO //dto 생성
                    = new BoardContentsDTO(id,
                    board.getMemberId(),
                    board.getAdmCd8(),
                    board.getTitle(),
                    board.getContents(),
                    board.getViewCount(),
                    board.getCreateTime(),
                    board.getWishCount(),
                    board.getStatus(),
                    board.getNickname(),
                    imageName,
                    board.getMember(),
                    board.getKoreaArea(),
                    board.getMemberImages()
                    , wishResult, report
            );
            model.addAttribute("contents", boardContentsDTO); //값 넘기기
            String memberId = "";
            if (principal != null) memberId = principal.getName();
            viewCountUp(id, memberId, request, response);

            return "/board/content";
        } else {
            return "redirect:/";
        }
    }

    // 게시글 수정 (GET)
    @GetMapping("/user/board/update")
    public String ContentUpdateForm(@RequestParam("id") Long id,
                                    Model model, Principal principal) {

        String principalId = "";
        if (principal != null) {
            principalId = principal.getName();
        }
        List<Board> board = boardService.findById(id, principalId);
        List<BoardImages> images = boardService.findImagesById(id);

        if (!board.isEmpty()) {

        Board oneBoard = board.get(0);

        Long memberId = oneBoard.getMemberId();
        Long compareId = Long.valueOf(principal.getName());
        if (!memberId.equals(compareId)) {
            return "redirect:/";
        }
        BoardUpdateDTO boardUpdateDTO1 =
                new BoardUpdateDTO(oneBoard.getId(),
                        images,
                        oneBoard.getTitle(),
                        oneBoard.getContents());

        model.addAttribute("boardUpdateDTO", boardUpdateDTO1);//값 넘기기
        return "board/UpdateForm";
        } else{
            String result = "redirect:/board/content?id=" + id;
            return result;
        }
    }
    //게시글 수정 POST
    @PostMapping("/user/board/update")
    @ResponseBody
    public HashMap<String, String> reFiles(@RequestParam("id") Long id,
                                           @RequestPart("files") List<MultipartFile> files,
                                           @RequestParam(value = "exist", required = false) List<String> beforeFiles,
                                           @RequestParam(value = "saved", required = false) List<String> ggFiles,
                                           BoardUpdateDTO boardUpdateDTO, Principal principal){

        HashMap<String, String> result = new HashMap<>();
        List<String> ggList = new ArrayList<>();

        String principalId = "";
        if (principal != null) {
            principalId = principal.getName();
        }
            List<Board> boardList = boardService.findById(id, principalId);

            if (!boardList.isEmpty()) {
                Board board = boardList.get(0);
                Long memberId = Long.valueOf(principal.getName());
                Long boardMemberId = board.getMemberId();// 아이디 비교 파라미터
                List<BoardImages> imagesList = boardService.findImagesById(id);
                if (memberId.equals(boardMemberId) && !imagesList.isEmpty()) { // 게시글과 작성자의 아이디가 동일
                    if (ggFiles != null) {
                        for(int i=0; i<ggFiles.size(); i++) {
                            awsS3Service.deleteFileInS3(ggFiles.get(i));//s3에서 삭제
                            boardService.deleteFile(ggFiles.get(i));
                        }
                    }


                    LocalDateTime now = LocalDateTime.now();
                    for (int i = 0; i < files.size(); i++) { //새로 들어온 파일
                        if (!files.get(i).isEmpty()) {
                            String originName = files.get(i).getOriginalFilename();//파일 이름
                            String encodeName = awsS3Service.upload(files.get(i), "img", 1570, 1047);//db에 들어갈 파일 이름
                            String decodeName = null;
                            try {
                                decodeName = URLDecoder.decode(encodeName, "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            BoardImages boardImages = new BoardImages(boardUpdateDTO.getId(), originName, decodeName, board.getCreateTime());//db에 들어갈 객체

                            boardService.insertBoardImage(boardImages);
                        }// 새로 들어온 파일 저장
                    }
                    boardService.update(boardUpdateDTO);
                }
                result.put("result", "200");
        } else {
            result.put("result", "300");
        }
        return result;
    }

    @GetMapping("/user/board/delete")
    public String ContentsDelete(@RequestParam Long id, Principal principal) {
        String principalId = "";
        if (principal != null) {
            principalId = principal.getName();
        }
        List<Board> deleteBoard = boardService.findById(id, principalId);//아이디로 보드 객체 뽑아오기
        String memberId = principal.getName();//principal로 멤버 아이디값 찾기

        Long memberLongId = Long.valueOf(memberId);// 멤버 아이디값 변환

        String result = "";
        if (!deleteBoard.isEmpty() && memberLongId.equals(deleteBoard.get(0).getMemberId())) {

            List<BoardImages> imagesList = boardService.findImagesById(id); //굿즈 이미지 객체 가져오기
            for (int i = 0; i < imagesList.size(); i++) {
                String savedFileName = imagesList.get(i).getSavedFileName();//각 파일의 저장된 이름을 찾아서
                awsS3Service.deleteFileInS3(savedFileName);//s3에서 삭제
            }
            boardService.delete(id);
            result = "redirect:/";
        } else {
            result = "redirect:/board";
        }

        return result;
    }


    //  게시글 작성 (GET)
    @GetMapping("/user/board/write")
    public String ContentWriteForm() {
        return "board/WriteForm";
    }


    //---------------------- 파일 확장자 체크 메서드
    public boolean fileExtensionCheck(String filename) {
        boolean result = false;
        String extension = filename.substring(filename.lastIndexOf(".") + 1);
        String[] good_extension = {"gif", "jpg", "jpeg", "png", "bmp", "ico", "apng"};
        for (int i = 0; i < good_extension.length; i++) {
            if (extension.equals(good_extension[i])) {
                result = true;
            }
        }
        return result;
    }//--------------------- file check end

    //  게시글 작성 (POST)
    @PostMapping("/user/board/write")
    @ResponseBody
    public HashMap<String, String> ContentWrite(@RequestPart("file") List<MultipartFile> files,
                                                @RequestParam("jsonData") String jsonData, Principal principal) {
        HashMap<String, String> result = new HashMap<>();
        //boardinsertDTO
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            if (!(originalFilename.equals(""))) {
                boolean b = fileExtensionCheck(originalFilename);
                if (!b) {
                    result.put("result", "400");
                    return result;
                }
            }
        }
        try {
            if (principal != null) {
                Long memberId = Long.valueOf(principal.getName());
                Member member = memberService.findById(memberId);

                // Member, jsonData에서 파라미터를 추출하여 Goods 객체 생성
//                Goods goods = insertCreateGoods(member, jsonData);
                Board board = insertBoard(member, jsonData);
                boardService.insert(board);


                LocalDateTime now = LocalDateTime.now();
                for (int i = 0; i < files.size(); i++) { //새로 들어온 파일
                    if (!files.get(i).isEmpty()) {
                        String originName = files.get(i).getOriginalFilename();//파일 이름
                        String encodeName = awsS3Service.upload(files.get(i), "img", 1570, 1047);//db에 들어갈 파일 이름
                        String decodeName = null;
                        try {
                            decodeName = URLDecoder.decode(encodeName, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        BoardImages images = new BoardImages(board.getId(), originName, decodeName, now);//db에 들어갈 객체

                        boardService.insertBoardImage(images);
                    }
                }
                result.put("result", "200");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "100");
        }
        return result;
    }

    public Board insertBoard(Member member, String jsonData) {
        ObjectMapper mapper = new ObjectMapper(); // json의 데이터
        JsonNode jsonResult = null;
        try {
            jsonResult = mapper.readTree(jsonData); // 해석 후 각 값이 담긴 객체 담기
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = jsonResult.get("title").asText();
        String contents = jsonResult.get("contents").asText();
        Board board = new Board( member.getId(),title,contents, member.getAdmCd8(), member.getNickName());

        return board;
    }


    //Wish
    @PostMapping("/user/board/wish")
    @ResponseBody
    public HashMap<String, String> wish(
            @RequestParam("id") long id,
            Principal principal) {
        HashMap<String, String> result = new HashMap<>();
        try {

            long loginId = 0L;
            if (principal != null) {
                loginId = Long.valueOf(principal.getName());
                LocalDateTime now = LocalDateTime.now();

                BoardWishList boardwishList = new BoardWishList(loginId, id, now);

                boardService.plusWish(boardwishList);

                result.put("result", "200");
            } else {
                result.put("result", "300");
            }


        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "100");
        }
        return result;
    }

    @PostMapping("/user/board/cancelWish")
    @ResponseBody
    public HashMap<String, String> deleteWish(@RequestParam("id") long id, Principal principal) {
        HashMap<String, String> result = new HashMap<>();

        try {
            Long userId = 0L;
            if (principal != null) {
                userId = Long.valueOf(principal.getName());
                boardService.deleteWish(userId, id);
                result.put("result", "200");
            } else {
                result.put("result", "300");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "100");
        }
        return result;
    }


    // report
    @GetMapping("/board/content/report")
    public String reportView(
            @RequestParam("id") Long boardId,
            Model model, Principal principal, HttpServletResponse response) throws IOException {
        if (principal == null) {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html; charset=utf-8");
            out.println("<script language='javascript'>");
            out.println("alert('로그인 후 이용해 주세요.');");
            out.println("self.close()");
            out.println("</script>");
            out.flush();
            return null;
        } else {
            model.addAttribute("oneBoardId", boardId);
            return "/board/boardReport";
        }
    }

    @PostMapping("/user/board/report")
    @ResponseBody
    public HashMap<String, String> sendReport(@RequestParam("id") Long boardId, Principal principal,
                                              @RequestBody HashMap<String, Object> jsonData) {
        HashMap<String, String> result = new HashMap<>();

        if (principal != null) {
            try {
                Long memberId = Long.valueOf(principal.getName());
                String content = (String) jsonData.get("content");
                BoardReportDTO boardReportDTO = new BoardReportDTO(memberId, boardId, content);
                boardService.sendReport(boardReportDTO);

                result.put("result", "200");
            } catch (Exception e) {
                result.put("result", "100");
            }
        } else {
            result.put("result", "100");
        }

        return result;
    }


    // 댓글 보기
    @ResponseBody
    @PostMapping("/board/content")
    public List<BoardComments> commentsList(@RequestParam("id") Long id, Principal principal) {
        List<BoardComments> boardComments = boardService.findCommentsById(id);

        List<BoardComments> newBoardComments = new ArrayList<>();
        String nowMember = "";
        if (principal != null) {
            nowMember = principal.getName();
        }
        for (int i = 0; i < boardComments.size(); i++) {
            BoardComments oneComments = new BoardComments().newBoardComments(boardComments.get(i), nowMember);
            newBoardComments.add(oneComments);

        }
        return newBoardComments;
    }

    // 댓글 작성 (POST)
    @PostMapping("/user/board/content/new")
    @ResponseBody
    public BoardCommentDTO commentWrite(@RequestParam("id") Long id, @RequestParam("content") String content, Principal principal) {
        Long mid = Long.valueOf(principal.getName()); // 로그인 되어있는 memberId 받아옴
        BoardCommentDTO boardCommentDTO = new BoardCommentDTO();
        boardCommentDTO.setMemberId(mid);
        boardCommentDTO.setBoardId(id);
        boardCommentDTO.setContents(content);

        int i = boardService.commentCreate(boardCommentDTO);
        BoardCommentDTO sendCommentDTO = boardService.Comments(mid, id);
        sendCommentDTO.setNowUser(String.valueOf(mid));

        return sendCommentDTO;

    }

    // 댓글 삭제 (POST)
    @PostMapping("/user/board/content/delete")
    @ResponseBody
    public HashMap<String, String> deleteComment(
            @RequestParam("id") Long id, Principal principal) {

        HashMap<String, String> result = new HashMap<>();
        Long mid = 0L;
        if (principal.getName() == null) {
            result.put("result", "400");
            return result;
        } else {
            mid = Long.valueOf(principal.getName());
            BoardComments boardComments = boardService.oneCommentById(id);
            if (mid.equals(boardComments.getMemberId())) {
                int i = boardService.commentDelete(id);
                result.put("result", "200");
            } else {
                result.put("result", "100");
                result.put("result", "100");

            }
            return result;
        }

    }

    // 댓글 업데이트 (POST)
    @PostMapping("/user/board/content/update")
    @ResponseBody
    public HashMap<String, Object> updateComment(
            @RequestParam("id") Long id,
            @RequestParam("content") String comment,
            Principal principal) {
        HashMap<String, Object> result = new HashMap<>();

        Long mid = 0L; // 로그인 되어있는 memberId 받아옴
        if (principal != null) {
            mid = Long.valueOf(principal.getName());
            BoardComments boardComments = boardService.oneCommentById(id);
            if (boardComments == null) {
                result.put("result", "400");
                return result;
            }
            if (mid.equals(boardComments.getMemberId())) {
                BoardComments setBoardComments = new BoardComments();
                setBoardComments.setMemberId(mid);
                setBoardComments.setId(id);
                setBoardComments.setContents(comment);

                boardService.commentUpdate(setBoardComments);
                BoardComments returnBoardComments = boardService.oneCommentById(id);
                result.put("result", "200");
                result.put("updateComment", returnBoardComments);
                return result;
            } else {
                result.put("result", "100");
                result.put("updateComment", boardComments);
                return result;
            }
        } else {
            result.put("result", "300");
            return result;
        }
    }


    @PostMapping("/board/searchBoards")
    @ResponseBody
    public List<BoardDTO> searchPaging(@RequestBody SearchType searchType, Principal principal) {
        String userId = "";
        boolean user = false;
        if (principal != null) {
            userId = principal.getName();
            user = true;
        }
        searchType.setId(userId);

        List<Board> board = boardService.pagingBoard(searchType);
        if (!board.isEmpty()) {
            List<BoardDTO> boardDTOList = new ArrayList<>();

            for (Board b : board) {
                BoardDTO boardDTO = new BoardDTO().MainBoard(b, user);
                boardDTOList.add(boardDTO);
            }
            return boardDTOList;
        } else {
            return new ArrayList<>();
        }
    }


    @GetMapping("/board/searchJuso")
    public String jusoWin() {
        return "/board/jusosearch";
    }


    //  주소 찾기
    @PostMapping("/board/searchJuso")
    @ResponseBody
    public List<JusoDTO> pushJuso(@RequestBody HashMap<String, String> juso) {
        List<JusoDTO> jusos = null;
        String pattern = "^[0-9ㄱ-ㅎ가-힣]{2,15}$";
        if (juso.get("juso") == null || juso.get("juso").isEmpty()) {
            return null;
        } else if (juso.get("juso").chars().allMatch(Character::isWhitespace)) {
            return null;
        } else if (juso.get("juso").matches(pattern)) {
            jusos = boardService.pullArea(juso.get("juso"));
        } else {
            return null;
        }
        return jusos;
    }


    // 조회수 카운트 업 처리
    private void viewCountUp(Long id, String memberId, HttpServletRequest request, HttpServletResponse response) {

        // request에 담겨있는 쿠키 중 로그인 한 경우와 안한 경우로 해당 문자열이 있는지 확인하고 Array에 담기
        ArrayList<Cookie> cookies = new ArrayList<>();
        Cookie[] getCookies = request.getCookies();
        if (getCookies != null) {
            for (Cookie c : getCookies) {
                if (memberId.equals("") && c.getName().contains("VBTO")) {
                    cookies.add(c);
                } else if (!memberId.equals("") && c.getName().contains("VBAU")) {
                    cookies.add(c);
                }
            }
        }

        // Array에 있을 경우 안의 쿠키를 하나씩 확인하면서 token이 있으면 값을 가져오기
        if (!cookies.isEmpty()) {
            int count = 0; // 모든 쿠키에 해당하는 문자열이 있는지 확인하기 때문에 없으면 count를 1씩 증가
            for (Cookie c : cookies) {
                if (c != null && !c.getValue().isEmpty()) { // 쿠키 내부가 비어 있는 경우에도 count 1 증가
                    String token = c.getValue();

                    // 토큰 안의 Claims 내용이 expired되었는지 확인하고 Claims 가져와서 body안의 goodsId 가져오기
                    if (jwtAuthenticationProvider.validateToken(token)) { // 토큰이 expired 되었으면 count 1 증가
                        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
                        String s = (String) claims.getBody().get("boardId");

                        // 아이디 있을 경우와 없을 경우로 나눠서 해당 문자열이 있는지 확인하고 없으면 count 1 증가
                        if (!memberId.equals("")) {
                            if (!s.contains("[" + memberId + "," + id + "]")) {
                                count++;
                            }
                        } else {
                            if (!s.contains("[" + id + "]")) {
                                count++;
                            }
                        }
                    } else {
                        count++;
                    }
                } else {
                    count++;
                }
            }

            // 쿠키가 비었는지, 토큰이 expired 되었는지, 문자열 확인하여 쿠키당 count가 1만 증가하므로 모든 쿠키에 해당 조건이 없다면 "count 숫자 = 쿠키의 숫자"
            if (count == cookies.size()) {
                Claims newClaims = Jwts.claims();
                Cookie cookie = cookies.get(cookies.size() - 1);
                if (!memberId.equals("")) {
                    if (cookie != null && !cookie.getValue().isEmpty()) {
                        String token = cookie.getValue();
                        if (jwtAuthenticationProvider.validateToken(token)) {
                            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
                            String boardIdList = claims.getBody().get("boardId") + "_[" + memberId + "," + id + "]";
                            createClaim(newClaims, memberId, boardIdList, id, response);
                        }
                    }
                } else {
                    if (cookie != null && !cookie.getValue().isEmpty()) {
                        String token = cookie.getValue();
                        if (jwtAuthenticationProvider.validateToken(token)) {
                            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
                            String boardIdList = claims.getBody().get("boardId") + "_[" + id + "]";
                            createClaim(newClaims, memberId, boardIdList, id, response);
                        }
                    }
                }
            }
        } else {
            // Array에 없을 경우 새로운 토큰과 쿠키를 생성하고 Claims를 담아 전달
            Claims newClaims = Jwts.claims();
            String boardIdList = "";
            if (!memberId.equals("")) {
                boardIdList = "[" + memberId + "," + id + "]";
            } else {
                boardIdList = "[" + id + "]";
            }
            createClaim(newClaims, memberId, boardIdList, id, response);
        }
    }

    public void createClaim(Claims newClaims, String memberId, String boardIdList, Long
            boardId, HttpServletResponse response) {
        newClaims.put("boardId", boardIdList);
        Date now = new Date();
        String newToken = Jwts.builder()
                .setClaims(newClaims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 1000L * 60 * 60 * 6))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        Cookie newCookie;
        Date d = new Date();
        // 시간 구분을 위해 Date에서 hour를 가져와 6으로 나눠 나머지 값을 Cookie뒤에 붙임(6시간)
        int hour = d.getHours() % 6;
        if (!memberId.equals("")) {
            newCookie = new Cookie("VBAU" + hour, newToken);
        } else {
            newCookie = new Cookie("VBTO" + hour, newToken);
        }

        newCookie.setValue(newToken);
        newCookie.setPath("/");
        newCookie.setMaxAge(60 * 60 * 6); // 6시간 시간 제한
        response.addCookie(newCookie);
        boardService.viewCountUp(boardId);
    }

}


