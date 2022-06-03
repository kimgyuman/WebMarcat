package marcat.board.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import marcat.board.vo.Board;
import marcat.board.vo.BoardComments;
import marcat.board.vo.BoardImages;
import marcat.goods.dto.SearchType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    void listAll() {
        List<Board> boards = boardService.listAll();
        System.out.println("boards = " + boards);
    }

    @Test
    void latestFourGoods() {
        String id = "";
        List<Board> boards = boardService.latestFourBoard(id);
        System.out.println("boards = " + boards);
    }

    @Test
    void pagingGoods() {
        SearchType searchType = new SearchType();
        searchType.setSearchType("w");
        searchType.setKeyword("");
        searchType.setStartNum("4");
        searchType.setKoreaArea("11110660");
        List<Board> boards = boardService.pagingBoard(searchType);
        System.out.println("boards = " + boards);
    }

//    @Test
//    void findById() {
//        List<Board> byId = boardService.findById(6L);
//        System.out.println("byId = " + byId);
//    }

//    @Test
//    void findCommentsById() {
//        List<BoardComments> commentsById = boardService.findCommentsById(2L);
//        System.out.println("commentsById = " + commentsById);
//    }

//    @Test
//    void findImagesById() {
//        List<BoardImages> imagesById = boardService.findImagesById(6L);
//        System.out.println("imagesById = " + imagesById);
//    }

//    @Test
//    void checkWish() {
//        int i = boardService.checkWish(30L, 6L);
//        System.out.println("i = " + i);
//    }
//    private final Long[] bnoArr = {7L,8L,9L,10L,11L};

//    @Test
//    void updateTest() {
//
//        IntStream.rangeClosed(1, 10).forEach(i -> {
//                BoardComments vo = new BoardComments();
//
//
//                vo.setId(bnoArr[i % 5]);
//                vo.setContents("댓글 테스트 " + i);
//                vo.setMemberId((long) i);
//                try {
//                    boardService.commentCreate(vo);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//        }
    }



//    @Test
//    void findByKeyword() {
//        SearchType searchType = new SearchType();
//        searchType.setSearchType("t");
//        searchType.setKeyword("test");
//        searchType.setStartNum("1");
//        List<Goods> byKeyword = goodsService.findByKeyword(searchType);
//        System.out.println("byKeyword = " + byKeyword);
//    }
