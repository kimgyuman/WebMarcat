package marcat.myPage.dao;

import marcat.board.vo.Board;
import marcat.board.vo.BoardComments;
import marcat.board.vo.BoardWishList;
import marcat.goods.dto.SearchType;
import marcat.goods.vo.*;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;
import marcat.members.vo.Message;
import marcat.myPage.dto.user.*;

import java.util.List;

public interface UserPageDAO {

    int userModify(Member member);

    int userDelete(Member member);

    int kakaoUserDelete(String uId);

    int userProfileImg(MemberImages memberImages);

    int userModifyPw(Member member);

    List<Goods> pagingReportGoods(SearchType searchType);

    List<Board> pagingReportBoard(SearchType searchType);

    List<Board> pagingBoard(SearchType searchType);

    //Paging Sell, SOLD Goods 12개 데이터
    List<Goods> pagingGoods(SearchType searchType);

    //Paging Request Goods 12개 데이터
    List<RequestBuy> pagingRequestGoods(SearchType searchType);

    //Paging Comment Goods 12개 데이터
    List<GoodsComments> pagingGoodsComment(SearchType searchType);

    //Paging My Request Goods 12개 데이터
    List<RequestBuy> pagingMyRequestGoods(SearchType searchType);

    //Paging My wanted Goods 12개 데이터
    List<WishList> pagingMyWantedGoods(SearchType searchType);

    //Paging My wanted Board 12개 데이터
    List<BoardWishList> pagingMyWantedBoard(SearchType searchType);

    List<BoardComments> pagingBoardComment(SearchType searchType);

    // 프로필 이미지로 아이디 찾기
    MemberImages findProfileImgById(Long memberId);

    // 닉네임으로 아이디 찾기
    Member findNickNameById(String nickName);

    UserLinkDTO findUserLinkById(Long id);

    int sendMessage(Message message);

    List<Goods> pagingActivityGoods(SearchType searchType);

    List<Board> pagingActivityBoard(SearchType searchType);

    void goodsRequestAccept(Long id);

    void goodsRequestRefuse(Long id);

    void deleteWish(Long id);

    void deleteBoardWish(Long id);

    void deleteRequest(Long id);

    List<Message> pagingSendMessage(MSearchType searchType);

    List<Message> pagingTargetMessage(MSearchType targetSearchType);

    Message findMessageById(Long msId);

    int deleteMessage(Long msId);

    void goodsStatusSelling(Long id);

    void goodsStatusSell(Long id);

    void goodsStatusSold(Long id);

    List<NotifyCountDTO> goodsNotifyCount(Long id);

    //멤버 닉네임 => 마켓닉네임과 같게 수정
    int updateGoodsNick(Goods goods);

    //멤버 닉네임 => 보드닉네임과 같게 수정
    int updateBoardNick(Board board);

    List<NotifyCountDTO> boardNotifyCount(Long id);

    List<BoardComments> findMyBoardComments(Long id);


}
