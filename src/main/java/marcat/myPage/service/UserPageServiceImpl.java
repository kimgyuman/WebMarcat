package marcat.myPage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.board.vo.Board;
import marcat.board.vo.BoardComments;
import marcat.board.vo.BoardWishList;
import marcat.goods.dto.SearchType;
import marcat.goods.vo.*;
import marcat.members.dto.JusoDTO;
import marcat.members.service.KakaoLoginService;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;
import marcat.members.vo.Message;
import marcat.myPage.dao.UserPageDAO;
import marcat.myPage.dto.user.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserPageServiceImpl implements UserPageService {

    private final UserPageDAO userPageDAO;
    private final KakaoLoginService kakao;


    @Override
    @Transactional
    public int userModify(Member member) {
        return userPageDAO.userModify(member);
    }

    @Override
    @Transactional
    public int userDelete(Member member) {
        return userPageDAO.userDelete(member);
    }

    @Override
    @Transactional
    public int kakaoUserDelete(String uId) {
        return userPageDAO.kakaoUserDelete(uId);
    }

    @Override
    public int userProfileImg(MemberImages memberImages) {
        return userPageDAO.userProfileImg(memberImages);
    }

    @Override
    @Transactional
    public int userModifyPw(Member member) {
        return userPageDAO.userModifyPw(member);
    }

    @Override
    public List<Goods> pagingReportGoods(SearchType searchType) {
        return userPageDAO.pagingReportGoods(searchType);
    }

    @Override
    public List<Board> pagingReportBoard(SearchType searchType) {
        return userPageDAO.pagingReportBoard(searchType);
    }

    @Override
    public List<Board> pagingBoard(SearchType searchType) {
        return userPageDAO.pagingBoard(searchType);
    }

    @Override
    public List<Goods> pagingGoods(SearchType searchType) {
        return userPageDAO.pagingGoods(searchType);
    }

    @Override
    public List<RequestBuy> pagingRequestGoods(SearchType searchType) {
        return userPageDAO.pagingRequestGoods(searchType);
    }

    @Override
    public List<GoodsComments> pagingGoodsComment(SearchType searchType) {
        return userPageDAO.pagingGoodsComment(searchType);
    }

    @Override
    public List<RequestBuy> pagingMyRequestGoods(SearchType searchType) {
        return userPageDAO.pagingMyRequestGoods(searchType);
    }

    @Override
    public List<WishList> pagingMyWantedGoods(SearchType searchType) {
        return userPageDAO.pagingMyWantedGoods(searchType);
    }

    @Override
    public List<BoardWishList> pagingMyWantedBoard(SearchType searchType) {
        return userPageDAO.pagingMyWantedBoard(searchType);
    }

    @Override
    public MemberImages findProfileImgById(Long memberId) {
        return userPageDAO.findProfileImgById(memberId);
    }

    @Override
    public Member findNickNameById(String nickName) {
        return userPageDAO.findNickNameById(nickName);
    }

    @Override
    public UserLinkDTO findUserLinkById(Long id) {
        return userPageDAO.findUserLinkById(id);
    }


    @Override
    public List<Goods> pagingActivityGoods(SearchType searchType) {
        return userPageDAO.pagingActivityGoods(searchType);
    }

    @Override
    public List<Board> pagingActivityBoard(SearchType searchType) {
        return userPageDAO.pagingActivityBoard(searchType);
    }

    @Override
    public List<Message> pagingSendMessage(MSearchType searchType) {
        return userPageDAO.pagingSendMessage(searchType);
    }

    @Override
    public List<Message> pagingTargetMessage(MSearchType targetSearchType) {
        return userPageDAO.pagingTargetMessage(targetSearchType);
    }

    @Override
    public List<BoardComments> pagingBoardComment(SearchType searchType) {
        return userPageDAO.pagingBoardComment(searchType);
    }

    @Override
    public Message findMessageById(Long msId) {
        return userPageDAO.findMessageById(msId);
    }

    @Override
    @Transactional
    public int deleteMessage(Long msId) {
        return userPageDAO.deleteMessage(msId);
    }

    @Override
    @Transactional
    public int updateGoodsNick(Goods goods) {
        return userPageDAO.updateGoodsNick(goods);
    }

    @Override
    @Transactional
    public int updateBoardNick(Board board) {
        return userPageDAO.updateBoardNick(board);
    }

    @Override
    @Transactional
    public int sendMessage(Message message) {
        return userPageDAO.sendMessage(message);
    }



}
