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
import marcat.goods.dto.SearchType;
import marcat.goods.service.GoodsService;
import marcat.goods.vo.*;
import marcat.members.vo.Message;
import marcat.myPage.dao.UserPageDAO;
import marcat.myPage.dto.user.*;
import marcat.myPage.service.UserPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class PagingController {

    private final UserPageService userPageService;
    private final GoodsService goodsService;
    private final AwsS3Service awsS3Service;
    private final BoardService boardService;
    private final UserPageDAO userPageDAO;


    /*  마켓 판매중 상품 페이징 */
    @PostMapping("/goodsHistory/sellingList")
    @ResponseBody
    public List<GoodsDTO> sellingPaging(@RequestBody SearchType searchType, Principal principal) {
        searchType.setId(principal.getName());
        List<Goods> goods = userPageService.pagingGoods(searchType);
        List<GoodsDTO> goodsDTOList = new ArrayList<>();
        for (Goods g : goods) {
            GoodsDTO goodsDTO = new GoodsDTO(g);
            goodsDTOList.add(goodsDTO);
        }
        return goodsDTOList;
    }

    /* 마켓 거래 요청 온 상품 페이징 */
    @PostMapping("/goodsHistory/requestList")
    @ResponseBody
    public List<RequestGoodsDTO> requestPaging(@RequestBody SearchType searchType, Principal principal) {
        searchType.setId(principal.getName());
        List<RequestGoodsDTO> requestGoodsList = new ArrayList<>();
        List<RequestBuy> requestBuys = userPageService.pagingRequestGoods(searchType);
        for (RequestBuy r : requestBuys) {
            RequestGoodsDTO requestGoodsDTO = new RequestGoodsDTO(r);
            requestGoodsList.add(requestGoodsDTO);
        }
        return requestGoodsList;
    }

    /* 마켓 댓글 페이징 */
    @PostMapping("/goodsHistory/commentsList")
    @ResponseBody
    public List<GoodsCommentsDTO> commentsPaging(@RequestBody SearchType searchType, Principal principal) {
        searchType.setId(principal.getName());
        List<GoodsCommentsDTO> commentList = new ArrayList<>();
        List<GoodsComments> goodsComments = userPageService.pagingGoodsComment(searchType);
        for (GoodsComments gc : goodsComments) {
            GoodsCommentsDTO goodsCommentsDTO = new GoodsCommentsDTO(gc);
            commentList.add(goodsCommentsDTO);
        }
        return commentList;
    }


    /* 마이페이지 활동내역 게시판 작성글 페이징 */
    @PostMapping("/activity/boardActivityList")
    @ResponseBody
    public List<BoardDTO> myBoardActivityPaging(@RequestBody SearchType searchType, Principal principal) {
        searchType.setId(principal.getName());

        List<BoardDTO> boardDTOList = new ArrayList<>();
        List<Board> boards = userPageService.pagingBoard(searchType);
        for (Board b : boards) {
            BoardDTO boardDTO = new BoardDTO(b);
            boardDTOList.add(boardDTO);
        }
        return boardDTOList;
    }

    /* 위시리스트 -> 내가 거래 요청한 상품 페이징 */
    @PostMapping("/wishList/myRequestList")
    @ResponseBody
    public List<RequestGoodsDTO> myRequestPaging(@RequestBody SearchType searchType, Principal principal) {
        searchType.setId(principal.getName());
        List<RequestGoodsDTO> requestGoodsList = new ArrayList<>();
        List<RequestBuy> requestBuys = userPageService.pagingMyRequestGoods(searchType);
        for (RequestBuy r : requestBuys) {
            RequestGoodsDTO requestGoodsDTO = new RequestGoodsDTO(r);
            requestGoodsList.add(requestGoodsDTO);
        }
        return requestGoodsList;
    }

    /* 마켓 위시리스트 페이징 */
    @PostMapping("/wishList/myWantedList")
    @ResponseBody
    public List<WishListDTO> myWantedPaging(@RequestBody SearchType searchType, Principal principal) {
        searchType.setId(principal.getName());
        List<WishListDTO> wishGoodsList = new ArrayList<>();
        List<WishList> wishLists = userPageService.pagingMyWantedGoods(searchType);
        for (WishList r : wishLists) {
            WishListDTO wishListDTO = new WishListDTO(r);
            wishGoodsList.add(wishListDTO);
        }
        return wishGoodsList;
    }

    /* 게시판 위시리스트 페이징 */
    @PostMapping("/wishList/myWantedBoardList")
    @ResponseBody
    public List<BoardWishListDTO> myWantedBoardPaging(@RequestBody SearchType searchType, Principal principal) {
        searchType.setId(principal.getName());
        List<BoardWishListDTO> wishBoardList = new ArrayList<>();
        List<BoardWishList> wishLists = userPageService.pagingMyWantedBoard(searchType);
        for(BoardWishList bw : wishLists){
            BoardWishListDTO boardWishListDTO = new BoardWishListDTO(bw);
            wishBoardList.add(boardWishListDTO);
        }
        return wishBoardList;
    }

    /* 마켓 신고 당한 글 페이징 */
    @PostMapping("/userReport/marketReportList")
    @ResponseBody
    public List<GoodsDTO> marketReportPaging(@RequestBody SearchType searchType, Principal principal) {
        searchType.setId(principal.getName());

        List<GoodsDTO> goodsDTOList = new ArrayList<>();
        List<Goods> goods = userPageService.pagingReportGoods(searchType);
        for (Goods g : goods) {
            GoodsDTO goodsDTO = new GoodsDTO(g);
            goodsDTOList.add(goodsDTO);
        }
        return goodsDTOList;
    }

    /* 게시판 신고 당한 글 페이징 */
    @PostMapping("/userReport/boardReportList")
    @ResponseBody
    public List<BoardDTO> boardReportPaging(@RequestBody SearchType searchType, Principal principal) {
        searchType.setId(principal.getName());

        List<BoardDTO> boardDTOList = new ArrayList<>();
        List<Board> boards = userPageService.pagingReportBoard(searchType);
        for (Board b : boards) {
            BoardDTO boardDTO = new BoardDTO(b);
            boardDTOList.add(boardDTO);
        }
        return boardDTOList;
    }

    /* userLink 마켓 활동로그 페이징 */
    @PostMapping("/userLink/MarketActivityPaging")
    @ResponseBody
    public List<GoodsDTO> MarketActivityPaging(
            @RequestBody SearchType searchType) {

        searchType.setId(searchType.getId());

        List<Goods> goods = userPageService.pagingActivityGoods(searchType);

        List<GoodsDTO> goodsDTOList = new ArrayList<>();
        for (Goods g : goods) {
            GoodsDTO goodsDTO = new GoodsDTO(g);
            goodsDTOList.add(goodsDTO);
        }

        return goodsDTOList;
    }

    /* userLink 게시판 활동로그 페이징 */
    @PostMapping("/userLink/boardActivityPaging")
    @ResponseBody
    public List<BoardDTO> boardActivityPaging(
            @RequestBody SearchType searchType) {
        searchType.setId(searchType.getId());

        List<BoardDTO> boardDTOList = new ArrayList<>();
        List<Board> boards = userPageService.pagingActivityBoard(searchType);

        for (Board b : boards) {
            BoardDTO boardDTO = new BoardDTO(b);
            boardDTOList.add(boardDTO);
        }

        return boardDTOList;
    }

    /* 보낸 쪽지 리스트 페이징 */
    @PostMapping("/message/sendMessageList")
    @ResponseBody
    public List<MessageDTO> sendMessagePaging(
            @RequestBody MSearchType searchType,
            Principal principal) {
        searchType.setSenderId(principal.getName());
        List<Message> sendMessages = userPageService.pagingSendMessage(searchType);
        List<MessageDTO> sendMessageDTOList = new ArrayList<>();
        for (Message sms : sendMessages) {
            MessageDTO messageDTO = new MessageDTO(sms);
            sendMessageDTOList.add(messageDTO);
        }
        return sendMessageDTOList;
    }

    /* 받은 쪽지 리스트 페이징 */
    @PostMapping("/message/receiveMessageList")
    @ResponseBody
    public List<MessageDTO> receiveMessagePaging(
            @RequestBody MSearchType targetSearchType,
            Principal principal) {
        targetSearchType.setTargetId(principal.getName());
        List<Message> receiveMessages = userPageService.pagingTargetMessage(targetSearchType);
        List<MessageDTO> receiveMessageDTOList = new ArrayList<>();
        for (Message rms : receiveMessages) {
            MessageDTO messageDTO = new MessageDTO(rms);
            receiveMessageDTOList.add(messageDTO);
        }
        return receiveMessageDTOList;
    }


}
