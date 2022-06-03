package marcat.board.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.board.dto.BoardCommentDTO;
import marcat.board.dto.CommentMemberDTO;
import marcat.board.vo.*;
import marcat.goods.dto.SearchType;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardDAOImpl implements BoardDAO{

    private final SqlSessionTemplate sqlSessionTemplate;
    private final String mapperLocation = "marcat.board.dao.BoardDAO.";

    //목록보기
    @Override
    public List<Board> ListAll() {
        return sqlSessionTemplate.selectList(mapperLocation + "AllBoard");
    }

    @Override
    public List<Board> latestFourBoard(String id) {
        HashMap<String , String > param = new HashMap<>();
        param.put("id", id);
        return sqlSessionTemplate.selectList(mapperLocation+"LatestFourBoard", param);
    }

    @Override
    public List<Board> pagingBoard(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingBoard", searchType);
    }

    @Override
    public List<Board> findById(Long id, String userId) {
        HashMap<String , String > param = new HashMap<>();
        param.put("id", String.valueOf(id));
        param.put("memberId", userId);
        return sqlSessionTemplate.selectList(mapperLocation+"FindBoardWithId",param);
    }

    @Override
    public List<BoardComments> findCommentsById(Long boardId) {
        return sqlSessionTemplate.selectList(mapperLocation+"FindCommentsWithId", boardId);
    }

    @Override
    public List<BoardImages> findImagesById(Long boardId) {
        return sqlSessionTemplate.selectList(mapperLocation+"FindImagesWithId", boardId);
    }

    @Override
    public int checkWish(long memberId, long boardId) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("memberId", memberId);
        parameter.put("boardId", boardId);
        return sqlSessionTemplate.selectOne(mapperLocation + "DupleWishList", parameter);
    }

    //글쓰기
    @Override
    public void insert(Board board) {
        sqlSessionTemplate.insert(mapperLocation + "insertBoard", board);
    }

    @Override
    public Board findLastBoard(Long id) {
        return sqlSessionTemplate.selectOne(mapperLocation + "lastValue", id);
    }

    @Override
    public Long insertBoardImage(BoardImages boardImages) {
        sqlSessionTemplate.insert(mapperLocation + "InsertImages", boardImages);
        return boardImages.getId();
    }

    //상세 보기
    @Override
    public Board FindBoardWithId(Long id) {
        return sqlSessionTemplate.selectOne(mapperLocation + "FindBoardWithId", id);

    }
    //삭제
    @Override
    public void delete(Long id) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);
//        parameter.put("memberId");
        sqlSessionTemplate.delete(mapperLocation + "deleteBoard", parameter);
    }
    //수정
    @Override
    public void update(Board board) {
        sqlSessionTemplate.update(mapperLocation + "updateBoard", board);
    }

    @Override
    public void updateImage(Board board) {
        sqlSessionTemplate.update(mapperLocation + "updateBoardImage", board);
    }

    @Override
    public void deleteFile(String saveName) {
        sqlSessionTemplate.delete(mapperLocation + "deleteFile", saveName);
    }

    /////////////////////////////comment 관련
    @Override
    public List<BoardComments> commentList(Integer id) {
        return sqlSessionTemplate.selectList(mapperLocation + "AllComments",id);
    }

    @Override
    public int commentCreate(BoardComments boardComments) {
        return sqlSessionTemplate.insert(mapperLocation + "createComment", boardComments);
    }

    @Override
    public int commentUpdate(BoardComments boardComments) {
        return sqlSessionTemplate.update(mapperLocation + "updateComment", boardComments);
    }

    @Override
    public int commentDelete(Long id) {
       return  sqlSessionTemplate.delete(mapperLocation + "deleteComment", id);
    }

    @Override
    public BoardComments oneCommentsById(Long id) {
        return sqlSessionTemplate.selectOne(mapperLocation + "oneCommentById", id);
    }

    @Override
    public CommentMemberDTO CommentMember(Long id){

        return sqlSessionTemplate.selectOne(mapperLocation+"CommentMember",id);

    }

    @Override
    public BoardComments Comment(Long id, Long boardId) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("memberId", id);
        parameter.put("boardId", boardId);
        return sqlSessionTemplate.selectOne(mapperLocation + "oneComment", parameter);
    }

    @Override
    public void plusWish(BoardWishList boardWishList) {
        sqlSessionTemplate.insert(mapperLocation + "plusWish", boardWishList);
    }

    @Override
    public void deleteWish(Long memberId, Long boardId) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("memberId", memberId);
        parameter.put("boardId", boardId);
        sqlSessionTemplate.delete(mapperLocation + "deleteWish", parameter);
    }

    @Override
    public BoardWishList findWish(Long memberId, Long boardId) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("memberId", memberId);
        parameter.put("boardId", boardId);
        return sqlSessionTemplate.selectOne(mapperLocation + "findWish", parameter);
    }

    @Override
    public void sendReport(BoardReport boardReport) {
        sqlSessionTemplate.insert(mapperLocation + "sendReport", boardReport);
    }

    @Override
    public BoardReport findReport(Long memberId, Long boardId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("memberId", memberId);
        hashMap.put("boardId", boardId);
        return sqlSessionTemplate.selectOne(mapperLocation + "oneReport", hashMap);
    }

    @Override
    public int viewCountUp(Long id) {
        return sqlSessionTemplate.update(mapperLocation + "viewCountUp", id);
    }


}
