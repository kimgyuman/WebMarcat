package marcat.goods.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.aws.AwsS3Service;
import marcat.goods.dto.*;
import marcat.goods.service.GoodsService;
import marcat.goods.vo.*;
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
import java.io.*;
import java.net.URLDecoder;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MarketController {
    // 유저정보가 필요할 때 맵핑해둘것 ("/user/") 관리자 쪽이면 ("/admin/")

    private final GoodsService goodsService;
    private final AwsS3Service goodsFileService;
    private final MemberService memberService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Value("#{auth['jwt_secret']}")
    private String secretKey;

    // 마켓 메인 화면 이동
    @GetMapping("/market")
    public String marketMain() {
        return "/market/marketMain";
    }

    // api방식으로 searchType 파라미터를 받아와 검색 및 페이징 처리
    @PostMapping("/market/searchGoods")
    @ResponseBody
    public List<GoodsDTO> searchPaging(@RequestBody SearchType searchType, Principal principal) {
        String userId = "";
        boolean user = false;
        if (principal != null) {
            userId = principal.getName();
            user = true;
        }
        searchType.setId(userId);

        List<Goods> goods = goodsService.pagingGoods(searchType);
        if (!goods.isEmpty()) {
            List<GoodsDTO> goodsDTOList = new ArrayList<>();


            for (Goods g : goods) {
                GoodsDTO goodsDTO = new GoodsDTO().plusGoodsDTO(g, user);
                goodsDTOList.add(goodsDTO);
            }

            return goodsDTOList;
        } else {
            return new ArrayList<>();
        }

    }

    //상세 페이지
    @GetMapping("/market/marketGoods")
    public String marketGoods(@RequestParam("id") long id, Model model, Principal principal, HttpServletRequest request, HttpServletResponse response) {
        String principalId = "";
        if (principal != null) {
            principalId = principal.getName();
        }
        List<Goods> searched = goodsService.findById(id, principalId); // 가져온 굿즈값
        List<GoodsImages> imageList = goodsService.findImagesById(id); // 굿즈의 이미지값
        List<String> imageName = new ArrayList<>(); // 객체에서 이름만 가져오기
        if (!searched.isEmpty()) {
            Goods searchedOne = searched.get(0); // 데이터가 중복으로 나타날 가능성이 있어 하나만 가져와 객체로 만들어주기
            boolean wishResult = false;
            boolean reportOne = false;
            boolean requestOne = false;

            if (principal != null) {

                reportOne = searchedOne.getReport().getContents() != null;

                wishResult = searchedOne.getWishList().getId() != null;

                requestOne = searchedOne.getRequestBuy().getId() != null;
            }

            for (int i = 0; i < imageList.size(); i++) { // 파일 이름 리스트에 넣기
                String savedFileName = imageList.get(i).getSavedFileName();
                imageName.add(savedFileName);
            }

            OneGoodsDTO oneGoodsDTO //dto 생성
                    = new OneGoodsDTO(id, searchedOne, imageName,
                    wishResult, reportOne, requestOne, searchedOne.getRequestBuy().getRequestStatus());

            if (principal != null) {
                model.addAttribute("member", true);
                model.addAttribute("memberName", Long.parseLong(principal.getName()));
            } else {
                model.addAttribute("member", false);
            }

            model.addAttribute("oneGoods", oneGoodsDTO); //값 넘기기
            String memberId = "";
            if (principal != null) memberId = principal.getName();
            viewCountUp(id, memberId, request, response); // 조회수 올리는 알고리즘
            return "market/marketGoods";
        } else {
            return "redirect:/market";
        }
    }// market details end

    /*------------------ move to createGoods page ------------------*/
    @GetMapping("/user/market/createGoods")
    public String toCreatePage() {
        return "/market/goods_input";
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


    //------------------------------------------createGoods start
    @PostMapping("/user/market/registerGoods")
    @ResponseBody
    public HashMap<String, String> createGoods(@RequestPart("file") List<MultipartFile> files,
                                               @RequestParam("jsonData") String jsonData, Principal principal) {

        HashMap<String, String> result = new HashMap<>();// 결과

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
        //결과 도출 시작
        try {
            if (principal != null) {
                Long memberId = Long.valueOf(principal.getName());
                Member member = memberService.findById(memberId);

                // Member, jsonData에서 파라미터를 추출하여 Goods 객체 생성
                Goods goods = insertCreateGoods(member, jsonData);
                goodsService.insertGoods(goods); //goods insert

                LocalDateTime now = LocalDateTime.now();
                for (int i = 0; i < files.size(); i++) { //새로 들어온 파일
                    if (!files.get(i).isEmpty()) {
                        String originName = files.get(i).getOriginalFilename();//파일 이름
                        String encodeName = goodsFileService.upload(files.get(i), "img", 1570, 1047);//db에 들어갈 파일 이름
                        String decodeName = null;
                        try {
                            decodeName = URLDecoder.decode(encodeName, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        GoodsImages goodsImages = new GoodsImages(goods.getId(), originName, decodeName, now);//db에 들어갈 객체

                        goodsService.insertGoodsImage(goodsImages);
                    }
                }
                result.put("result", "200");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "100");
        }
        return result;
    }//----------------------------------------------------createGoods end

    //삭제
    @GetMapping("/user/market/marketGoods/DeleteGoods")
    public String goodsDelete(@RequestParam("id") long id, Principal principal) {
        String principalId = "";
        if (principal != null) {
            principalId = principal.getName();
        }
        List<Goods> delGoods = goodsService.findById(id, principalId);//아이디로 굿즈 객체 뽑아오기
        String memberId = principal.getName();//principal로 멤버 아이디값 찾기

        Long memberLongId = Long.valueOf(memberId);// 멤버 아이디값 변환

        String result = "";
        if (!delGoods.isEmpty() && memberLongId.equals(delGoods.get(0).getMemberId())) {

            List<GoodsImages> imagesList = goodsService.findImagesById(id); //굿즈 이미지 객체 가져오기
            for (GoodsImages goodsImages : imagesList) {
                String savedFileName = goodsImages.getSavedFileName();//각 파일의 저장된 이름을 찾아서
                goodsFileService.deleteFileInS3(savedFileName);//s3에서 삭제
            }
            goodsService.deleteGoodsById(id);
            result = "redirect:/market";
        } else {
            result = "redirect:/market/marketGoods?id=" + id;
        }

        return result;
    }

    //수정
    @GetMapping("/user/market/GoodsRemake")
    public String goodsRemake(@RequestParam("id") long id,
                              Principal principal, Model model) {
        String principalId = "";
        if (principal != null) {
            principalId = principal.getName();
        }
        List<Goods> goods = goodsService.findById(id, principalId);
        List<GoodsImages> images = goodsService.findImagesById(id);

        if (!goods.isEmpty()) {
            Goods oneGoods = goods.get(0);

            Long memberId = oneGoods.getMemberId();
            Long compareId = Long.valueOf(principal.getName());
            if (!(Objects.equals(memberId, compareId))) {
                String result = "redirect:/market/marketGoods?id=" + id;
                return result;
            }

            RemakeGoods remakeGoods =
                    new RemakeGoods(oneGoods.getId(),
                            images,
                            oneGoods.getCategories(),
                            oneGoods.getTitle(),
                            oneGoods.getPrice(),
                            oneGoods.getContents(),
                            oneGoods.getNegoStatus());

            model.addAttribute("oneGoods", remakeGoods);//값 넘기기
            return "market/GoodsRemake";
        } else {
            String result = "redirect:/market/marketGoods?id=" + id;
            return result;
        }
    }

    // Goods 수정 처리
    @PostMapping("/user/market/updateGoods")
    @ResponseBody
    public HashMap<String, String> reFiles(@RequestParam("id") long id,
                                           @RequestPart("files") List<MultipartFile> files,
                                           @RequestParam(value = "deleteList", required = false) List<String> deleteFiles,
                                           UpdateGoodsDTO updateGoodsDTO, Principal principal
    ) {

        HashMap<String, String> result = new HashMap<>();//결과
        String principalId = "";
        if (principal != null) {
            principalId = principal.getName();
        }
        List<Goods> forFindMember = goodsService.findById(id, principalId);

        if (!forFindMember.isEmpty()) {
            Goods goods = forFindMember.get(0);
            Long memberId = Long.valueOf(principal.getName());
            Long goodsMemberId = goods.getMemberId();// 아이디 비교 파라미터
            if (memberId.equals(goodsMemberId)) { // 게시글과 작성자의 아이디가 동일
                if (deleteFiles != null) {
                    for (int i = 0; i < deleteFiles.size(); i++) { //현존 파일 지우기
                        String savedFileName = deleteFiles.get(i);//각 파일의 저장된 이름을 찾아서
                        goodsFileService.deleteFileInS3(savedFileName);//s3에서 삭제
                        goodsService.deleteImgInDB(savedFileName);
                    }//현존 파일 지우기 완료
                }

                if (!files.isEmpty()) {
                    LocalDateTime now = LocalDateTime.now();
                    for (int i = 0; i < files.size(); i++) { //새로 들어온 파일

                        String originName = files.get(i).getOriginalFilename();//파일 이름
                        String encodeName = goodsFileService.upload(files.get(i), "img", 1570, 1047);//db에 들어갈 파일 이름
                        String decodeName = null;
                        try {
                            decodeName = URLDecoder.decode(encodeName, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        GoodsImages goodsImages = new GoodsImages(id, originName, decodeName, now);//db에 들어갈 객체
                        goodsService.insertGoodsImage(goodsImages);
                    }

                }// 새로 들어온 파일 저장
                goodsService.remakeGoodsById(updateGoodsDTO);//db 업데이트 하기
                result.put("result", "200");
            }else {
                result.put("result", "100");
            }
        }


        return result;
    }


    //comments
    @PostMapping("/market/getMarketComment")
    @ResponseBody
    public List<AllMarketCommentsDTO> getMarketComments(@RequestBody HashMap<String, Object> hashMap,
                                                        Principal principal) {

        List<GoodsComments> goodsComments = goodsService.pagingComments(hashMap);

        List<AllMarketCommentsDTO> allMarketCommentsDTOList = new ArrayList<>();
        String nowMember = "";
        if (principal != null) {
            nowMember = principal.getName();
        }

        for (int i = 0; i < goodsComments.size(); i++) {
            AllMarketCommentsDTO allMarketCommentsDTO = new AllMarketCommentsDTO(goodsComments.get(i), nowMember);
            allMarketCommentsDTOList.add(allMarketCommentsDTO);
        }

        return allMarketCommentsDTOList;
    }// getComments end

    // create comments
    @PostMapping("/user/market/createMarketComment")
    @ResponseBody
    public List<AllMarketCommentsDTO> createMarketComments(
            @RequestBody HashMap<String, Object> hashMap,
            Principal principal) {

        long memberId = 0L;
        LocalDateTime now = null;
        long id = 0L;
        String content = null;

        HashMap<String, Object> forRepage = new HashMap<>();
        List<AllMarketCommentsDTO> allMarketCommentsDTOList = new ArrayList<>();
        if (principal != null) {
            memberId = Long.parseLong(principal.getName());
            now = LocalDateTime.now();
            id = Long.parseLong(String.valueOf(hashMap.get("id")));
            content = String.valueOf(hashMap.get("content"));


            List<Goods> byId = goodsService.findById(id, principal.getName());

            String status = byId.get(0).getStatus();
            if (status.isEmpty()) {
                allMarketCommentsDTOList = null;
                return allMarketCommentsDTOList;
            }

            if (status.equals(ViewStatus.INACTIVE.getValue())) {
                allMarketCommentsDTOList = null;
                return allMarketCommentsDTOList;
            }


            forRepage.put("id", id);
            forRepage.put("startNum", 1);

            GoodsComments goodsComments = new GoodsComments(memberId, id, content, now);
            goodsService.createMarketComments(goodsComments);//데이터 베이스에 댓글 전송

        }
        List<GoodsComments> commentsByGoodsId = goodsService.pagingComments(forRepage);

        String nowUser = principal.getName();
        for (int i = 0; i < commentsByGoodsId.size(); i++) {
            AllMarketCommentsDTO allMarketCommentsDTO = new AllMarketCommentsDTO(commentsByGoodsId.get(i), nowUser);
            allMarketCommentsDTOList.add(allMarketCommentsDTO);
        }
        return allMarketCommentsDTOList;
    }

    @PostMapping("/user/market/deleteMarketComment")
    @ResponseBody
    public HashMap<String, Object> deleteComment(
            @RequestBody HashMap<String, Object> hashMap, Principal principal) {

        HashMap<String, Object> resultHash = new HashMap<>();

        if (principal != null) {
            Long id = Long.parseLong(String.valueOf(hashMap.get("id")));
            Long memberId = Long.valueOf(principal.getName());

            GoodsComments goodsComments = goodsService.selectOneComments(id);
            if (goodsComments == null) {
                resultHash.put("result", "400");
                return resultHash;
            }
            Long memberId1 = goodsComments.getMemberId();

            if (memberId.equals(memberId1)) {
                goodsService.deleteMarketComments(id);
                resultHash.put("result", "200");
            } else {
                resultHash.put("result", "100");
            }

        } else {
            resultHash.put("result", "300");
        }

        return resultHash;
    }

    @PostMapping("/user/market/updateMarketComment")
    @ResponseBody
    public HashMap<String, Object> updateMarketComment(@RequestBody HashMap<String, Object> hashMap,
                                                       Principal principal) {

        HashMap<String, Object> resultHash = new HashMap<>();
        if (principal != null) {
            Long id = Long.valueOf(String.valueOf(hashMap.get("id")));
            Long memberId = Long.valueOf(principal.getName());
            GoodsComments goodsComments = goodsService.selectOneComments(id);
            if (goodsComments == null) {
                resultHash.put("result", "400");
                return resultHash;
            }

            if (memberId.equals(goodsComments.getMemberId())) {
                goodsService.updateMarketComments(hashMap);
                GoodsComments resultComments = goodsService.selectOneComments(id);
                resultHash.put("result", "200");
                resultHash.put("updateComment", resultComments);
                return resultHash;
            } else {
                resultHash.put("result", "100");
                resultHash.put("updateComment", goodsComments);
                return resultHash;
            }
        } else {
            resultHash.put("result", "300");
            return resultHash;
        }


    }

    //찜
    @PostMapping("/user/market/wish")
    @ResponseBody
    public HashMap<String, String> wish(@RequestParam("id") long goodsId, Principal principal) {

        HashMap<String, String> result = new HashMap<>();
        String principalId = "";

        Long userId = 0L;
        if (principal != null) {

            principalId = principal.getName();
            List<Goods> searched = goodsService.findById(goodsId, principalId);

            if (searched.isEmpty()) {
                result.put("result", "400");
                return result;
            } else if (searched.get(0).getWishList().getId() != null) {
                result.put("result", "500");
                return result;
            }
            String status = searched.get(0).getStatus();

            if (status.equals(ViewStatus.INACTIVE.getValue())) {
                result.put("result", "100");
                return result;
            }

            userId = Long.valueOf(principal.getName());
            LocalDateTime now = LocalDateTime.now();
            WishList wishList = new WishList(userId, goodsId, now);
            goodsService.plusWish(wishList);

            result.put("result", "200");
            return result;
        } else {
            result.put("result", "300");
            return result;
        }
    }

    @PostMapping("/user/market/cancelWish")
    @ResponseBody
    public HashMap<String, String> deleteWish(@RequestParam("id") long goodsId, Principal principal) {
        HashMap<String, String> result = new HashMap<>();


        Long userId = 0L;
        String principalId = "";
        if (principal != null) {
            principalId = principal.getName();
            List<Goods> searched = goodsService.findById(goodsId, principalId);
            if (searched.isEmpty()) {
                result.put("result", "400");
                return result;
            } else if (searched.get(0).getWishList().getId() == null) {
                result.put("result", "500");
                return result;
            }
            String status = searched.get(0).getStatus();

            if (status.equals(ViewStatus.INACTIVE.getValue())) {
                result.put("result", "100");
                return result;
            }

            userId = Long.valueOf(principal.getName());
            goodsService.deleteWish(userId, goodsId);
            result.put("result", "200");
        } else {
            result.put("result", "300");
        }


        return result;
    }

    //------------------------------report start -------------------------
    @GetMapping("/user/market/marketGoods/report")
    public String reportWin(@RequestParam("id") Long goodsId, Model model, Principal principal) {
        if (principal == null) {
            return "/market/marketMain";
        } else {
            model.addAttribute("oneGoodsId", goodsId);
            return "/market/reportModal";
        }
    }

    @PostMapping("/user/market/report")
    @ResponseBody
    public HashMap<String, String> sendReport(@RequestParam("id") Long goodsId, Principal principal,
                                              @RequestBody HashMap<String, Object> jsonData) {
        HashMap<String, String> result = new HashMap<>();

        if (principal != null) {
            String principalId = "";
            principalId = principal.getName();
            List<Goods> searched = goodsService.findById(goodsId, principalId);

            if (searched.isEmpty()) {
                result.put("result", "400");
                return result;
            } else if (searched.get(0).getReport().getContents() != null) {
                result.put("result", "500");
                return result;
            }

            String status = searched.get(0).getStatus();
            if (status.equals(ViewStatus.INACTIVE.getValue())) {
                result.put("result", "300");
                return result;
            }


            Long memberId = Long.valueOf(principal.getName());
            String content = (String) jsonData.get("content");
            Report report = new Report(memberId, goodsId, content);
            goodsService.sendReport(report);
            result.put("result", "200");
            return result;
        } else {
            result.put("result", "100");
            return result;
        }
    }


    //------------------------------findJuso start--------------------------
    @GetMapping("/market/searchJuso")
    public String jusoWin() {
        return "/market/jusosearch";
    }

    @PostMapping("/market/searchJuso")
    @ResponseBody
    public List<JusoDTO> pushJuso(@RequestBody HashMap<String, String> juso) {
        List<JusoDTO> jusos = null;
        String pattern = "^[0-9ㄱ-ㅎ가-힣]{2,15}$";
        if (juso.get("juso") == null || juso.get("juso").isEmpty()) {
            return null;
        } else if (juso.get("juso").chars().allMatch(Character::isWhitespace)) {
            return null;
        } else if (juso.get("juso").matches(pattern)) {
            jusos = goodsService.pullArea(juso.get("juso"));
        } else {
            return null;
        }
        return jusos;
    }
//------------------------------findJuso end-----------------------


    //------------------------------buy request------------------------
    @PostMapping("/user/market/sendRequest")
    @ResponseBody
    public HashMap<String, String> sendRequst(@RequestParam("id") long goodsId,
                                              @RequestParam("sendRequest") String request,
                                              Principal principal) {
        HashMap<String, String> result = new HashMap<>();

        Long memberId = Long.valueOf(principal.getName());
        RequestBuy buyRequest = goodsService.findRequest(memberId, goodsId);
        List<Goods> finded = goodsService.findById(goodsId, "");


        if ((finded.get(0).getMemberId()).equals(Long.parseLong(principal.getName()))) {
            result.put("result", "400");
            return result;
        }


        if (buyRequest != null) {
            if (buyRequest.getRequestStatus().equals("거절됨")) {
                result.put("result", "300");
                return result;
            }

            result.put("result", "100");
            return result;
        } else {
            RequestBuy requestBuy = new RequestBuy(memberId, goodsId, request);
            goodsService.sendRequest(requestBuy);
            result.put("result", "200");
            return result;
        }
    }

    // goods 생성에서 json파라미터의 값을 추출하여 Goods 객체 반환
    public Goods insertCreateGoods(Member member, String jsonData) {
        ObjectMapper mapper = new ObjectMapper(); // json의 데이터
        JsonNode jsonResult = null;
        try {
            jsonResult = mapper.readTree(jsonData); // 해석 후 각 값이 담긴 객체 담기
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = jsonResult.get("title").asText();
        String stringPrice = jsonResult.get("price").asText();
        int price = Integer.parseInt(stringPrice);
        String contents = jsonResult.get("contents").asText();
        String category = jsonResult.get("category").asText();
        String nego = jsonResult.get("nego").asText();
        Goods goods = new Goods(member.getId(), member.getAdmCd8(),
                category, title, contents, SellStatus.SELL.getValue(),
                0, null, price, nego, 0,
                null, member.getNickName());
        return goods;
    }

    // 조회수 카운트 업 처리
    private void viewCountUp(Long id, String memberId, HttpServletRequest request, HttpServletResponse response) {

        // request에 담겨있는 쿠키 중 로그인 한 경우와 안한 경우로 해당 문자열이 있는지 확인하고 Array에 담기
        ArrayList<Cookie> cookies = new ArrayList<>();
        Cookie[] getCookies = request.getCookies();
        if (getCookies != null) {
            for (Cookie c : getCookies) {
                if (memberId.equals("") && c.getName().contains("VTOKEN")) {
                    cookies.add(c);
                } else if (!memberId.equals("") && c.getName().contains("VAUTH")) {
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
                        String s = (String) claims.getBody().get("goodsId");

                        // 아이디 있을 경우와 없을 경우로 나눠서 해당 문자열이 있는지 확인
//                        if (!memberId.equals("")) {
//                            if (!s.contains("[" + memberId + "," + id + "]")) {
//                                Claims newClaims = Jwts.claims();
//                                String goodsId = claims.getBody().get("goodsId") + "_[" + memberId + "," + id + "]";
//                                updateClaim(newClaims, goodsId, id, c, response);
//                            } else {
//                                count++;
//                            }
//                        } else {
//                            if (!s.contains("[" + id + "]")) {
//                                Claims newClaims = Jwts.claims();
//                                String goodsId = claims.getBody().get("goodsId") + "_[" + id + "]";
//                                updateClaim(newClaims, goodsId, id, c, response);
//                            } else {
//                                count++;
//                            }
//                        }
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
                            String goodsIdList = claims.getBody().get("goodsId") + "_[" + memberId + "," + id + "]";
                            createClaim(newClaims, memberId, goodsIdList, id, response);
                        }
                    }
                } else {
                    if (cookie != null && !cookie.getValue().isEmpty()) {
                        String token = cookie.getValue();
                        if (jwtAuthenticationProvider.validateToken(token)) {
                            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
                            String goodsIdList = claims.getBody().get("goodsId") + "_[" + id + "]";
                            createClaim(newClaims, memberId, goodsIdList, id, response);
                        }
                    }
                }
            }
        } else {
            // Array에 없을 경우 새로운 토큰과 쿠키를 생성하고 Claims를 담아 전달
            Claims newClaims = Jwts.claims();
            String goodsIdList = "";
            if (!memberId.equals("")) {
                goodsIdList = "[" + memberId + "," + id + "]";
            } else {
                goodsIdList = "[" + id + "]";
            }
            createClaim(newClaims, memberId, goodsIdList, id, response);
//            newClaims.put("goodsId", goodsIdList);
//            Date now = new Date();
//            String newToken = Jwts.builder()
//                    .setClaims(newClaims)
//                    .setIssuedAt(now)
//                    .setExpiration(new Date(now.getTime() + 1000L * 60 * 60 * 6))
//                    .signWith(SignatureAlgorithm.HS256, secretKey)
//                    .compact();
//            Cookie newCookie;
//            Date d = new Date();
//            // 시간 구분을 위해 Date에서 hour를 가져와 6으로 나눠 나머지 값을 Cookie뒤에 붙임(6시간)
//            int hour = d.getHours() % 6;
//            if (!memberId.equals("")) {
//                newCookie = new Cookie("VAUTH" + hour, newToken);
//            } else {
//                newCookie = new Cookie("VTOKEN" + hour, newToken);
//            }
//
//            newCookie.setPath("/");
//            newCookie.setMaxAge(60 * 60 * 6); // 6시간 시간 제한
//            response.addCookie(newCookie);
//            goodsService.viewCountUp(id);
        }
    }

    public void createClaim(Claims newClaims, String memberId, String goodsIdList, Long
            goodsId, HttpServletResponse response) {
        newClaims.put("goodsId", goodsIdList);
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
            newCookie = new Cookie("VAUTH" + hour, newToken);
        } else {
            newCookie = new Cookie("VTOKEN" + hour, newToken);
        }

        newCookie.setValue(newToken);
        newCookie.setPath("/");
        newCookie.setMaxAge(60 * 60 * 6); // 6시간 시간 제한
        response.addCookie(newCookie);
        goodsService.viewCountUp(goodsId);
    }
}
