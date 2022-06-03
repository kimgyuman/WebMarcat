package marcat.myPage.service;

import marcat.board.vo.Board;
import marcat.board.vo.BoardComments;
import marcat.board.vo.BoardWishList;
import marcat.goods.dto.SearchType;
import marcat.goods.vo.*;
import marcat.members.dto.JusoDTO;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;
import marcat.members.vo.Message;
import marcat.myPage.dto.user.*;

import java.util.List;

public interface UserPageService {


    /*유저 정보 수정 => 수정되면 1반환*/
    int userModify(Member member);

    /*유저 정보 삭제 => 실제로 삭제 X, 비활성화 업데이트 => 수정되면 1반환*/
    int userDelete(Member member);

    /* 카카오 유저 삭제 */
    int kakaoUserDelete(String uId);

    /* 프로필 이미지 업데이트 */
    int  userProfileImg(MemberImages memberImages);

    /* 비밀번호 수정 */
    int  userModifyPw(Member member);

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

    //쪽지 전송
    int sendMessage(Message message);

    //프로필 이미지로 멤버아이디 찾기
    MemberImages findProfileImgById(Long memberId);

    //닉네임으로 멤버아이디 찾기
    Member findNickNameById(String nickName);

    UserLinkDTO findUserLinkById(Long id);

    List<Goods> pagingActivityGoods(SearchType searchType);

    List<Board> pagingActivityBoard(SearchType searchType);

    List<Message> pagingSendMessage(MSearchType searchType);

    List<Message> pagingTargetMessage(MSearchType targetSearchType);

    List<BoardComments> pagingBoardComment(SearchType searchType);

    Message findMessageById(Long msId);

    int deleteMessage(Long msId);

    //멤버 닉네임 => 마켓닉네임과 같게 수정
    int updateGoodsNick(Goods goods);

    //멤버 닉네임 => 보드닉네임과 같게 수정
    int updateBoardNick(Board board);

}


