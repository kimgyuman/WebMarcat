package marcat.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.board.dao.BoardDAO;
import marcat.board.dto.*;
import marcat.board.vo.*;
import marcat.goods.dto.SearchType;
import marcat.members.dao.MemberDAO;
import marcat.members.dto.JusoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("boardService")
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService{
    //Autowired로 DAO와 연결시킴. Controller-Service-DAO로 연결되는 구조임.

    private final BoardDAO boardDAO;
    private final MemberDAO memberDAO;


    @Override
    public List<Board> listAll() {
        return boardDAO.ListAll();
    }

    @Override
    public List<Board> latestFourBoard(String id) {
        return boardDAO.latestFourBoard(id);
    }

    @Override
    public List<Board> pagingBoard(SearchType searchType) {
        return boardDAO.pagingBoard(searchType);
    }

    @Override
    public List<Board> findById(Long id, String userId) {
        return boardDAO.findById(id, userId);
    }

    @Override
    public List<BoardComments> findCommentsById(Long boardId) {
        return boardDAO.findCommentsById(boardId);
    }

    @Override
    public List<BoardImages> findImagesById(Long boardId) {
        return boardDAO.findImagesById(boardId);
    }

    @Override
    public int checkWish(long memberId, long boardId) {
        return boardDAO.checkWish(memberId, boardId);
    }

    @Override
    @Transactional
    public void insert(Board board) {  // 컨트롤러에서 받아온 DTO를  알맞게 처리하고 VO에 담아 DAO로 넘김.
        boardDAO.insert(board);
    }

    @Override
    public Board lastValue(Long id) {
        return boardDAO.findLastBoard(id);
    }

    @Override
    @Transactional
    public void insertBoardImage(BoardImages boardImages) {
        boardDAO.insertBoardImage(boardImages);
    }

    @Override
    public Board FindBoardWithId(Long id,String userId) {
        return boardDAO.FindBoardWithId(id);
    }

    @Override
    public Board read(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        boardDAO.delete(id);
    }

    @Override
    @Transactional
    public void update(BoardUpdateDTO boardUpdateDTO) {
        Board board = new Board(boardUpdateDTO);
        boardDAO.update(board);
    }

    @Override
    @Transactional
    public void updateImage(BoardUpdateDTO boardUpdateDTO) {
        Board board = new Board(boardUpdateDTO);
        boardDAO.updateImage(board);
    }

    @Override
    @Transactional
    public void deleteFile(String savedName) {
        boardDAO.deleteFile(savedName);
    }

    //comment
    @Override
    public List<BoardComments> commentList(Integer id) {
        return boardDAO.commentList(id);
    }

    @Override
    @Transactional
    public int commentCreate(BoardCommentDTO boardCommentDTO) {
        BoardComments boardComments = new BoardComments(boardCommentDTO);
        return boardDAO.commentCreate(boardComments);
    }

    @Override
    @Transactional
    public int commentUpdate(BoardComments boardComments) {
        return boardDAO.commentUpdate(boardComments);
    }

    @Override
    @Transactional
    public int commentDelete(Long id) {
        return boardDAO.commentDelete(id);
    }

    @Override
    public BoardComments oneCommentById(Long id) {
        return boardDAO.oneCommentsById(id);
    }

    @Override
    public CommentMemberDTO CommentMember(Long id) {
        return boardDAO.CommentMember(id);
    }

    @Override
    public BoardCommentDTO Comments(Long id, Long boardId) {
        BoardCommentDTO comment =new BoardCommentDTO(boardDAO.Comment(id, boardId)) ;
        return comment;
    }

    @Override
    @Transactional
    public void plusWish(BoardWishList boardWishList) {
        boardDAO.plusWish(boardWishList);

    }

    @Override
    @Transactional
    public void deleteWish(Long memberId, Long boardId) {
        boardDAO.deleteWish(memberId, boardId);

    }

    @Override
    public BoardWishList findWish(Long memberId, Long boardId) {
        return boardDAO.findWish(memberId, boardId);
    }

    @Override
    @Transactional
    public void sendReport(BoardReportDTO boardReportDTO) {
        BoardReport boardReport = new BoardReport(boardReportDTO);
        boardDAO.sendReport(boardReport);
    }

    @Override
    public BoardReport findReport(Long memberId, Long boardId) {
        return boardDAO.findReport(memberId, boardId);
    }

    @Override
    public List<JusoDTO> pullArea(String searchJuso) {
        return memberDAO.findJuso(searchJuso);
    }

    @Override
    @Transactional
    public int viewCountUp(Long id) {
            return boardDAO.viewCountUp(id);
    }


}
