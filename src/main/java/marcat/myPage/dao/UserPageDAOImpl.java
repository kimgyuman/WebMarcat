package marcat.myPage.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.board.vo.Board;
import marcat.board.vo.BoardComments;
import marcat.board.vo.BoardWishList;
import marcat.goods.dto.SearchType;
import marcat.goods.vo.*;
import marcat.members.dto.JusoDTO;
import marcat.members.vo.*;
import marcat.myPage.dto.user.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserPageDAOImpl implements UserPageDAO {

    private final SqlSessionTemplate sqlSessionTemplate;
    private final String mapperLocation = "marcat.myPage.dao.UserPageDAO.";


    /*수정시 수정한 행을 반환하므로 int*/
    @Override
    public int userModify(Member member) {
        return sqlSessionTemplate.update(mapperLocation+"UpdateMember", member);
    }

    @Override
    public int userDelete(Member member) {
        return sqlSessionTemplate.update(mapperLocation+"DeleteMember", member);
    }

    @Override
    public int kakaoUserDelete(String uId) {
        return sqlSessionTemplate.update(mapperLocation+"DeleteKakaoMember", uId);
    }

    /*프로필사진 업데이트*/
    @Override
    public int userProfileImg(MemberImages memberImages) {
        return sqlSessionTemplate.update(mapperLocation+"MemberImageUpdate",memberImages);
    }

    // 비밀번호 변경
    @Override
    public int userModifyPw(Member member) {
        return sqlSessionTemplate.update(mapperLocation+"pwUpdate",member);
    }

    @Override
    public List<Goods> pagingReportGoods(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingReportGoods", searchType);
    }

    @Override
    public List<Board> pagingReportBoard(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingReportBoard", searchType);
    }


    @Override
    public List<Board> pagingBoard(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingBoard", searchType);
    }

    @Override
    public List<Goods> pagingGoods(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingGoods", searchType);
    }

    @Override
    public List<Goods> pagingActivityGoods(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingActivityGoods", searchType);
    }

    @Override
    public List<Board> pagingActivityBoard(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingActivityBoard", searchType);
    }

    @Override
    public List<RequestBuy> pagingRequestGoods(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingRequestGoods", searchType);
    }

    @Override
    public List<GoodsComments> pagingGoodsComment(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingGoodsComment", searchType);
    }

    @Override
    public List<RequestBuy> pagingMyRequestGoods(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingMyRequestGoods", searchType);
    }

    @Override
    public List<WishList> pagingMyWantedGoods(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingMyWantedGoods", searchType);
    }

    @Override
    public List<BoardWishList> pagingMyWantedBoard(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingMyWantedBoard", searchType);
    }


    @Override
    public List<BoardComments> pagingBoardComment(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingBoardComment", searchType);
    }

    @Override
    public MemberImages findProfileImgById(Long memberId) {
        return sqlSessionTemplate.selectOne(mapperLocation+"FindProfileImgById",memberId);
    }

    @Override
    public Member findNickNameById(String nickName) {
        return sqlSessionTemplate.selectOne(mapperLocation+"FindNickNameById", nickName);
    }

    @Override
    public UserLinkDTO findUserLinkById(Long id) {
        return sqlSessionTemplate.selectOne(mapperLocation+"FindUserLinkById",id);
    }

    @Override
    public int sendMessage(Message message) {
        int result = sqlSessionTemplate.insert(mapperLocation+"SendMessage",message);
        return result;
    }

    @Override
    public List<Message> pagingSendMessage(MSearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingSendMessage", searchType);
    }

    @Override
    public List<Message> pagingTargetMessage(MSearchType targetSearchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingTargetMessage", targetSearchType);
    }

    @Override
    public Message findMessageById(Long msId) {
        return sqlSessionTemplate.selectOne(mapperLocation+"FindMessageById", msId);
    }

    @Override
    public int deleteMessage(Long msId) {
        return sqlSessionTemplate.delete(mapperLocation+"DeleteMessage", msId);
    }

    @Override
    public void goodsRequestAccept(Long id) {
        sqlSessionTemplate.update(mapperLocation+"RequestAccept",id);
    }

    @Override
    public void goodsRequestRefuse(Long id) {
        sqlSessionTemplate.update(mapperLocation+"RequestRefuse",id);

    }

    @Override
    public void deleteWish(Long id) {
        sqlSessionTemplate.delete(mapperLocation+"DeleteWish",id);
    }

    @Override
    public void deleteBoardWish(Long id) {
        sqlSessionTemplate.delete(mapperLocation+"DeleteBoardWish",id);
    }

    @Override
    public void deleteRequest(Long id) {
        sqlSessionTemplate.delete(mapperLocation+"DeleteRequest",id);
    }

    @Override
    public void goodsStatusSelling(Long id) {
        sqlSessionTemplate.update(mapperLocation+"GoodsStatusSelling",id);
    }

    @Override
    public void goodsStatusSell(Long id) {
        sqlSessionTemplate.update(mapperLocation+"GoodsStatusSell",id);
    }

    @Override
    public void goodsStatusSold(Long id) {

        sqlSessionTemplate.update(mapperLocation+"GoodsStatusSold",id);
    }

    @Override
    public List<NotifyCountDTO> goodsNotifyCount(Long id) {
        return sqlSessionTemplate.selectList(mapperLocation+"GoodsNotifyCount",id);
    }

    @Override
    public int updateGoodsNick(Goods goods) {
        return sqlSessionTemplate.update(mapperLocation+"UpdateGoodsNick",goods);
    }

    @Override
    public int updateBoardNick(Board board) {
        return sqlSessionTemplate.update(mapperLocation+"UpdateBoardNick",board);
    }

    @Override
    public List<NotifyCountDTO> boardNotifyCount(Long id) {
        return sqlSessionTemplate.selectList(mapperLocation+"BoardNotifyCount",id);
    }

    @Override
    public List<BoardComments> findMyBoardComments(Long id) {
        return sqlSessionTemplate.selectList(mapperLocation+"MyBoardComments",id);
    }

}
