package marcat.board.service;

import marcat.board.dto.*;
import marcat.board.vo.*;
import marcat.goods.dto.SearchType;
import marcat.members.dto.JusoDTO;

import java.util.List;

public interface BoardService {

    List<Board> listAll();

    //최근 Board 6개 데이터
    List<Board> latestFourBoard(String id);

    //Paging Board 6개 데이터
    List<Board> pagingBoard(SearchType searchType);

    //id로 찾아오기
    List<Board> findById(Long id, String userId);

    //Comments boardId로 가져오기
    List<BoardComments> findCommentsById(Long boardId);

    //Images boardId로 가져오기
    List<BoardImages> findImagesById(Long boardId);

    // whishList member의 중복 확인
    int checkWish(long memberId, long boardId);


    /*게시글 관련*/

    // 게시글 작성
    void insert(Board board); // 컨트롤러에서 받아온 DTO

    Board lastValue(Long id);

    void insertBoardImage(BoardImages boardImages);

    // 게시글 수정
    void update(BoardUpdateDTO boardUpdateDTO);

    void updateImage(BoardUpdateDTO boardUpdateDTO);

    void deleteFile(String savedName);

    // 상세보기
    Board FindBoardWithId(Long id, String userId);

    Board read(Long id);

    void delete(Long id);



    /* 댓글 관련 */

    List<BoardComments> commentList(Integer id);

    int commentCreate(BoardCommentDTO boardCommentsDTO);

    int commentUpdate(BoardComments boardComments);

    int commentDelete(Long id);

    BoardComments oneCommentById(Long id);

    CommentMemberDTO CommentMember(Long id);

//    BoardCommentDTO Comments(Long id);

    BoardCommentDTO Comments(Long id, Long boardId);

    /* wish 관련 */
    void plusWish(BoardWishList boardWishList);

    void deleteWish(Long memberId, Long boardId);

    BoardWishList findWish(Long memberId, Long boardId);

    /*  report 관련   */
    void sendReport(BoardReportDTO boardReportDTO);

    BoardReport findReport(Long memberId, Long boardId);

    // search juso
    List<JusoDTO> pullArea(String searchJuso);

    int viewCountUp(Long id);

//    List<BoardListDTO> FindByAllBoardList();
//    public void Insert(BoardInsertDTO boardInsertDTO);

}
