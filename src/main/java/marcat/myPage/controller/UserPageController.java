package marcat.myPage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.aws.AwsS3Service;
import marcat.board.dto.BoardDTO;
import marcat.board.service.BoardService;
import marcat.board.vo.Board;
import marcat.board.vo.BoardComments;
import marcat.board.vo.BoardImages;
import marcat.board.vo.BoardWishList;
import marcat.goods.dto.GoodsCommentsDTO;
import marcat.goods.dto.GoodsDTO;
import marcat.goods.service.GoodsService;
import marcat.goods.vo.*;
import marcat.members.service.MemberService;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;
import marcat.members.vo.Message;
import marcat.myPage.dao.UserPageDAO;
import marcat.myPage.dto.user.*;
import marcat.goods.dto.SearchType;
import marcat.myPage.service.UserPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserPageController {

    private final UserPageService userPageService;
    private final MemberService memberService;
    private final AwsS3Service profileService;
    private final GoodsService goodsService;
    private final BoardService boardService;
    private final AwsS3Service awsS3Service;
    private final UserPageDAO userPageDAO;

    /* 마이페이지 메뉴 ) 마켓 상품 거래내역 */
    @GetMapping("/goodsHistory")
    public String userPageGoodsHistory(Model model, Principal principal) {
        SearchType searchType = new SearchType();
        searchType.setSellStatus("SELL");
        searchType.setStartNum("1");
        searchType.setId(principal.getName());

        List<GoodsDTO> sellingGoodsList = new ArrayList<>();
        List<Goods> goods = userPageService.pagingGoods(searchType);
        for (Goods g : goods) {
            GoodsDTO goodsDTO = new GoodsDTO(g);
            sellingGoodsList.add(goodsDTO);
        }

        List<RequestGoodsDTO> requiredGoodsList = new ArrayList<>();
        List<RequestBuy> requestBuys = userPageService.pagingRequestGoods(searchType);
        for (RequestBuy r : requestBuys) {
            RequestGoodsDTO requestGoodsDTO = new RequestGoodsDTO(r);
            requiredGoodsList.add(requestGoodsDTO);
        }

        List<GoodsDTO> soldGoodsList = new ArrayList<>();
        searchType.setSellStatus("SOLD");
        List<Goods> goods1 = userPageService.pagingGoods(searchType);
        for (Goods g : goods1) {
            GoodsDTO goodsDTO = new GoodsDTO(g);
            soldGoodsList.add(goodsDTO);
        }

        List<MyGoodsCommentDTO> commentList = new ArrayList<>();
        List<GoodsComments> goodsComments = userPageService.pagingGoodsComment(searchType);
        for (GoodsComments gc : goodsComments) {
            MyGoodsCommentDTO myGoodsCommentDTO = new MyGoodsCommentDTO(gc);
            commentList.add(myGoodsCommentDTO);
        }

        model.addAttribute("sellingGoodsList", sellingGoodsList);
        model.addAttribute("soldGoodsList", soldGoodsList);
        model.addAttribute("requiredGoodsList", requiredGoodsList);
        model.addAttribute("commentList", commentList);
        return "/myPage/user/userPageGoodsHistory";
    }

    /* 마이페이지 메뉴 ) 게시판 활동내역 */
    @GetMapping("/activityLog")
    public String userPageActivityLog(Model model, Principal principal) {
        SearchType searchType = new SearchType();
        searchType.setStartNum("1");
        searchType.setId(principal.getName());

        List<BoardDTO> myActivityBoardList = new ArrayList<>();
        List<Board> myActivityBoards = userPageService.pagingActivityBoard(searchType);

        for(Board b : myActivityBoards){
            BoardDTO boardDTO = new BoardDTO(b);
            myActivityBoardList.add(boardDTO);
        }

        List<MyBoardCommentDTO> myActivityBoardCommentList = new ArrayList<>();
        List<BoardComments> myActivityBoardComments = userPageService.pagingBoardComment(searchType);

        for(BoardComments bc : myActivityBoardComments){
            MyBoardCommentDTO myBoardCommentDTO = new MyBoardCommentDTO(bc);
            myActivityBoardCommentList.add(myBoardCommentDTO);
        }

        model.addAttribute("myActivityBoardList",myActivityBoardList);
        model.addAttribute("myActivityBoardCommentList",myActivityBoardCommentList);
        return "/myPage/user/userPageActivityLog";
    }

    /* 마이페이지 메뉴 ) 위시리스트 */
    @GetMapping("/wishList")
    public String userPageWishList(Model model, Principal principal) {
        SearchType searchType = new SearchType();
        searchType.setStartNum("1");
        searchType.setId(principal.getName());

        List<WishListDTO> wishGoodsList = new ArrayList<>();
        List<WishList> wishLists = userPageService.pagingMyWantedGoods(searchType);
        for (WishList r : wishLists) {
            WishListDTO wishListDTO = new WishListDTO(r);
            wishGoodsList.add(wishListDTO);
        }

        List<BoardWishListDTO> boardWishList = new ArrayList<>();
        List<BoardWishList> boardWishLists = userPageService.pagingMyWantedBoard(searchType);
        for(BoardWishList bw : boardWishLists){
            BoardWishListDTO boardWishListDTO = new BoardWishListDTO(bw);
            boardWishList.add(boardWishListDTO);
        }

        List<RequestGoodsDTO> requiredGoodsList = new ArrayList<>();
        List<RequestBuy> requestBuys = userPageService.pagingMyRequestGoods(searchType);
        for (RequestBuy r : requestBuys) {
            RequestGoodsDTO requestGoodsDTO = new RequestGoodsDTO(r);
            requiredGoodsList.add(requestGoodsDTO);
        }

        model.addAttribute("wishGoodsList", wishGoodsList);
        model.addAttribute("boardWishList",boardWishList);
        model.addAttribute("requiredGoodsList", requiredGoodsList);
        return "/myPage/user/userPageWishList";
    }

    /* 마이페이지 메뉴 ) 신고 당한 내역 */
    @GetMapping("/report")
    public String userPageReport(Model model, Principal principal) {
        SearchType searchType = new SearchType();
        searchType.setSellStatus("SELL");
        searchType.setStartNum("1");
        searchType.setId(principal.getName());

        List<GoodsDTO> goodsReportList = new ArrayList<>();
        List<Goods> goods = userPageService.pagingReportGoods(searchType);
        for (Goods g : goods) {
            GoodsDTO goodsDTO = new GoodsDTO(g);
            goodsReportList.add(goodsDTO);
        }

        SearchType searchType1 = new SearchType();
        searchType1.setId(principal.getName());
        searchType1.setStartNum("1");

        List<BoardDTO> boardReportList = new ArrayList<>();
        List<Board> board = userPageService.pagingReportBoard(searchType1);
        for (Board b : board) {
            BoardDTO boardDTO = new BoardDTO(b);
            boardReportList.add(boardDTO);
        }

        model.addAttribute("goodsReportList", goodsReportList);
        model.addAttribute("boardReportList", boardReportList);

        return "/myPage/user/userPageReport";
    }

    /* userLink 유저 활동 내역 */
    @GetMapping("/userLink")
    public String userPageUserLinkId (
            @RequestParam("id") Long id,
            Model model){

        //id 클릭시 멤버 정보를 가져옴
        Member member = memberService.findById(id);

        // 이미지 정보를 멤버아이디로 가져옴
        MemberImages mi = userPageService.findProfileImgById(member.getId());

        //유저 링크정보 멤버아이디로 가져옴
        UserLinkDTO userLinkDTO = userPageService.findUserLinkById(member.getId());

        SearchType searchType = new SearchType();
        searchType.setStartNum("1");
        searchType.setId(String.valueOf(id));

        List<GoodsDTO> marketActivityList = new ArrayList<>();
        List<Goods> goods = userPageService.pagingActivityGoods(searchType);
        for (Goods g : goods){
            GoodsDTO goodsDTO = new GoodsDTO(g);
            marketActivityList.add(goodsDTO);
        }

        List<BoardDTO> boardActivityList = new ArrayList<>();
        List<Board> boards = userPageService.pagingActivityBoard(searchType);
        for (Board b : boards){
            BoardDTO boardDTO = new BoardDTO(b);
            boardActivityList.add(boardDTO);
        }

        model.addAttribute("marketActivityList",marketActivityList);
        model.addAttribute("boardActivityList",boardActivityList);
        model.addAttribute("userLinkDTO", userLinkDTO);
        return "myPage/user/userPageUserLink";
    }

    /* 마이페이지 메뉴 ) 쪽지함 */
    @GetMapping("/message")
    public String userPageMessage (Model model, Principal principal) {
        Member member = memberService.findById(Long.valueOf(principal.getName()));
        MemberImages memberImages = userPageService.findProfileImgById(Long.valueOf(principal.getName()));
        MSearchType searchType = new MSearchType();
        MSearchType targetSearchType = new MSearchType();

        targetSearchType.setStartNum("1");
        targetSearchType.setTargetId(principal.getName());
        targetSearchType.setTargetNick(member.getNickName());
        targetSearchType.setTargetMemberImages(memberImages.getSavedFileName());

        searchType.setStartNum("1");
        searchType.setSenderId(principal.getName());
        searchType.setSenderNick(member.getNickName());
        searchType.setSenderMemberImages(memberImages.getSavedFileName());

        /* 내가보낸 메세지 */
        List<MessageDTO> receiveMessageList = new ArrayList<>();
        List<Message> receiveMessages = userPageService.pagingTargetMessage(targetSearchType);

        for (Message ms : receiveMessages) {
            MessageDTO messageDTO = new MessageDTO(ms);
            receiveMessageList.add(messageDTO);
        }

        /* 상대가 보낸 메세지 */
        List<MessageDTO> sendMessageList = new ArrayList<>();
        List<Message> sendMessages = userPageService.pagingSendMessage(searchType);

        for (Message ms : sendMessages) {
            MessageDTO messageDTO = new MessageDTO(ms);
            sendMessageList.add(messageDTO);
        }

        model.addAttribute("sendMessageList",sendMessageList);
        model.addAttribute("receiveMessageList",receiveMessageList);
        return "/myPage/user/userPageMessage";
    }

    /* 쪽지 보내기 PopUp */
    @GetMapping("/userLink/sendMessage")
    public String userPageUserLinkSendMessageMain (
            @RequestParam Long id,
            Model model, Principal principal) {

        //보내는 사람 정보 받아오기
        Member mSendId = memberService.findById(Long.valueOf(principal.getName()));
        //받는 사람 정보 받아오기
        Member mRecvId = memberService.findById(id);

        model.addAttribute("mSendId",mSendId.getNickName());
        model.addAttribute("mRecvId",mRecvId.getNickName());
        return "/myPage/user/userPageUserLinkPopUp";
    }

    /* 쪽지 보내기 */
    @PostMapping("/userLink/sendMessage")
    @ResponseBody
    public int sendMessage(
            @RequestBody HashMap<String,Object> request,
            Principal principal) {

        // 리턴 맵으로 JSON
        String getSendNickName = (String) request.get("sendId");
        String getRecvNickName = (String) request.get("recvId");

        Member sendMember = userPageService.findNickNameById(getSendNickName);
        Long sendId = sendMember.getId();

        Member targetMember = userPageService.findNickNameById(getRecvNickName);
        Long targetId = targetMember.getId();

        String message = String.valueOf(request.get("message"));

        LocalDateTime now = LocalDateTime.now();

        Message insertMessage = new Message(sendId, targetId, message, now);
        int resultNum = userPageService.sendMessage(insertMessage);
        return resultNum;
    }

    /* 답장하기 PopUp */
    @GetMapping("/message/replyMessage")
    public String userPageReceiveMessageMain (
            @RequestParam Long msId,
            Model model, Principal principal) {

        Message message = userPageService.findMessageById(msId);

        //보내는 사람 정보 받아오기
        Member reSendId = memberService.findById(Long.valueOf(message.getSenderId()));

        //받는 사람 정보 받아오기
        Member reRecvId = memberService.findById(Long.valueOf(message.getTargetId()));

        model.addAttribute("msId",msId);
        model.addAttribute("reSendId",reSendId.getNickName());
        model.addAttribute("reRecvId",reRecvId.getNickName());
        return "/myPage/user/userPageReplyPopUp";
    }

    /* 답장하기 */
    @PostMapping("/message/replyMessage")
    @ResponseBody
    public int userPageReceiveMessage(
            @RequestBody HashMap<String,Object> reply,
            Principal principal) {

        // 리턴 맵으로 JSON
        String getSendNickName = (String) reply.get("sendId");
        String getRecvNickName = (String) reply.get("recvId");

        Member sendMember = userPageService.findNickNameById(getSendNickName);
        Long sendId = sendMember.getId();

        Member targetMember = userPageService.findNickNameById(getRecvNickName);
        Long targetId = targetMember.getId();

        String message = String.valueOf(reply.get("message"));

        LocalDateTime now = LocalDateTime.now();

        Message insertMessage = new Message(sendId, targetId, message, now);

        int resultNum = userPageService.sendMessage(insertMessage);
        return resultNum;
    }

    /* 받은 쪽지 삭제 */
    @PostMapping("/message/delMessage")
    @ResponseBody
    public int deleteMessage(@RequestParam("msId") Long msId) {
        int result = userPageService.deleteMessage(msId);
        return result;
    }

    /* 보낸 쪽지 삭제 */
    @PostMapping("/message/sendDelMessage")
    @ResponseBody
    public int sendDeleteMessage(@RequestParam("msId") Long msId){
        int result = userPageService.deleteMessage(msId);
        return result;
    }

    /* 판매중 마켓글 삭제 */
    @PostMapping("/deleteMyGoods")
    @ResponseBody
    public String deleteMyGoods(@RequestParam("id") Long id,Principal principal) {
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
                awsS3Service.deleteFileInS3(savedFileName);//s3에서 삭제
            }
            goodsService.deleteGoodsById(id);
        }
        return "ok";
    }

    /* 마켓글 거래 요청 수락하기 */
    @PostMapping("/accept")
    @ResponseBody
    public String RequestAccept(@RequestParam("requestId") Long rid,
                                @RequestParam("goodsId") Long gid) {
        userPageDAO.goodsRequestAccept(rid);
        userPageDAO.goodsStatusSelling(gid);
        return "ok";
    }

    /* 마켓글 나에게 요청 온 거래 거절하기 */
    @PostMapping("/refuse")
    @ResponseBody
    public String RequestRefuse(@RequestParam("id") Long id) {
        userPageDAO.goodsRequestRefuse(id);
        return "ok";
    }

    /* 마켓글 판매완료 수락하기 */
    @PostMapping("/soldAccept")
    @ResponseBody
    public String soldAccept(@RequestParam("requestId") Long rid,
                             @RequestParam("goodsId") Long gid) {
        userPageDAO.goodsStatusSold(gid);

        return "ok";
    }

    /* 상대방과 판매중인 상태에서 판매 거절하기 */
    @PostMapping("/deleteAccept")
    @ResponseBody
    public String deleteAccept(@RequestParam("requestId") Long rid,
                               @RequestParam("goodsId") Long gid) {
        userPageDAO.goodsStatusSell(gid);
        return "ok";
    }

    /* 마켓글 위시리스트 취소하기 */
    @PostMapping("/deleteWish")
    @ResponseBody
    public String deleteWish(@RequestParam("id") Long id) {
        userPageDAO.deleteWish(id);
        return "ok";
    }

    /* 게시글 위시리스트 취소하기 */
    @PostMapping("/deleteBoardWish")
    @ResponseBody
    public String deleteBoardWish(@RequestParam("id") Long id) {
        userPageDAO.deleteBoardWish(id);
        return "ok";
    }

    /* 내가 요청한 거래 취소하기 */
    @PostMapping("/deleteRequest")
    @ResponseBody
    public String deleteRequest(@RequestParam("id") Long id) {
        userPageDAO.deleteRequest(id);
        return "ok";
    }

    /* 마이페이지 활동내역 게시글 삭제 */
    @PostMapping("/deleteMyBoard")
    @ResponseBody
    public String deleteMyBoard(@RequestParam("id") Long id,
                                Principal principal) {
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

    /* 마켓글 신고횟수 카운트 */
    @PostMapping("/findNotifyCount")
    @ResponseBody
    public List<NotifyCountDTO> findNotifyCount(@RequestParam("goodsId") Long id) {
        List<NotifyCountDTO> notifyCountDTOS = userPageDAO.goodsNotifyCount(id);
        return notifyCountDTOS;
    }

    /* 게시글 신고횟수 카운트 */
    @PostMapping("/findBoardNotifyCount")
    @ResponseBody
    public List<NotifyCountDTO> findBoardNotifyCount(@RequestParam("boardId") Long id) {
        List<NotifyCountDTO> notifyCountDTOS = userPageDAO.boardNotifyCount(id);
        return notifyCountDTOS;
    }

}
