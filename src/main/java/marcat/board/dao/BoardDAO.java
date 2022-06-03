package marcat.board.dao;

import marcat.board.dto.BoardCommentDTO;
import marcat.board.dto.CommentMemberDTO;
import marcat.board.vo.*;
import marcat.goods.dto.SearchType;

import java.util.List;

public interface BoardDAO {

    List<Board> ListAll(); // 전체 목록 조회

    //최근 Board 4개 데이터
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


//    List<BoardListDTO> FindByAllBoardList();

    //글쓰기
    void insert(Board board); // 서비스 레이어에서 가공한 DTO를 디비에 저장할거임.

    //최근 게시물 id로 찾아오기
    Board findLastBoard(Long id);

    //BoardImage insert
    Long insertBoardImage(BoardImages boardImages);

    Board FindBoardWithId(Long id); //
    //삭제

    void delete(Long id); // 삭제

    void update(Board board); // 게시글 수정
    void updateImage(Board board); // 게시글 이미지 수정
    void deleteFile(String saveName);

    /////////댓글 관련 메서드
    List<BoardComments> commentList(Integer id);

    int commentCreate(BoardComments boardComments);

    int commentUpdate(BoardComments boardComments);

    int commentDelete(Long id);

    BoardComments oneCommentsById(Long id);

     CommentMemberDTO CommentMember(Long id);

    BoardComments Comment(Long id, Long boardId);


    //wish 관련
    void plusWish(BoardWishList boardWishList);

    void deleteWish(Long memberId, Long boardId);

    BoardWishList findWish(Long memberId, Long boardId);

//    report 관련

    void sendReport(BoardReport boardReport);

    BoardReport findReport(Long memberId, Long boardId);

    int viewCountUp(Long id);

}
