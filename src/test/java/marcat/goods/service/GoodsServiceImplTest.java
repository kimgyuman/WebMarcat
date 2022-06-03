package marcat.goods.service;

import lombok.extern.slf4j.Slf4j;
import marcat.goods.dto.SearchType;
import marcat.goods.vo.Goods;
import marcat.goods.vo.GoodsComments;
import marcat.goods.vo.GoodsImages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
class GoodsServiceImplTest {

    @Autowired
    private GoodsService goodsService;

    @Test
    void latestSixGoods() {
        String id = "";
        List<Goods> goods = goodsService.latestSixGoods(id);
        System.out.println("goods.size() = " + goods.size());
        System.out.println("goods = " + goods);
    }

    @Test
    void pagingGoods() {
        SearchType searchType = new SearchType();
        searchType.setSearchType("tw");
        searchType.setKeyword("");
        searchType.setStartNum("1");
        searchType.setCategories("");
        searchType.setKoreaArea("");
        log.info("searchType={}", searchType);
        List<Goods> goods = goodsService.pagingGoods(searchType);
        System.out.println("goods.size() = " + goods.size());
        System.out.println("goods = " + goods);
    }

    @Test
    void findById() {
        String goodsId = "";
        List<Goods> byId = goodsService.findById(227L , goodsId);
        int size = byId.size();
        System.out.println("goods size = " + size);
    }

    @Test
    void findCommentsById() {
        List<GoodsComments> commentsById = goodsService.findCommentsById(21L);
        System.out.println("commentsById = " + commentsById);
    }

    @Test
    void findImagesById() {
        List<GoodsImages> imagesById = goodsService.findImagesById(21L);
        System.out.println("imagesById = " + imagesById);
    }

    @Test
    void checkWish() {
        int i = goodsService.checkWish(28L, 21L);
        System.out.println("i = " + i);
    }

    @Test
    void findOneGoods() {
    }

    @Test
    void findByKeyword() {
        SearchType searchType = new SearchType();
        searchType.setSearchType("t");
        searchType.setKeyword("test");
        searchType.setStartNum("1");
        List<Goods> byKeyword = goodsService.findByKeyword(searchType);
        System.out.println("byKeyword = " + byKeyword);
    }

    @Test
    void findByMemberId() {
        List<Goods> byMemberId = goodsService.findByMemberId(52L);
        System.out.println("byMemberId = " + byMemberId);
    }

    @Test
    void findCommentsByMemberId() {
        List<GoodsComments> commentsByMemberId = goodsService.findCommentsByMemberId(52L);
        System.out.println("commentsByMemberId = " + commentsByMemberId);
    }

    @Test
    void insertGoods() {
    }

    @Test
    void 문자열자름(){
        String url = "https://djadlbucket.s3.ap-northeast-2.amazonaws.com/img/7a5c2cbf-6aba-4c1e-9cc5-b92b090ccba7갤럭시 버즈 라이브.jpeg";
        String url1 = "https://djadlbucket.s3.ap-northeast-2.amazonaws.com/";

        String replace = url.replace(url1, "");
        System.out.println("replace = " + replace);
//        int i = url.indexOf("/");
//        String substring = url.substring(51);
//        System.out.println("substring = " + substring);
//        System.out.println("i = " + i);

    }
    @Test
    void pagingComments(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("startNum", 1);
        hashMap.put("id", 285);
        List<GoodsComments> goodsComments = goodsService.pagingComments(hashMap);
        for (int i = 0; i < goodsComments.size(); i++) {
            System.out.println(goodsComments.get(i));
            log.info(goodsComments.get(i).getMemberImages().getSavedFileName());
        }
    }

    @Test
    void category_list() {
    }

    @Test
    void viewCountUp(){
        List<Goods> byId = goodsService.findById(227L, "");
        log.info("byId.viewCount={}", byId.get(0).getViewCount());
        int i = goodsService.viewCountUp(227L);
        log.info("i={}", i);
        List<Goods> byId2 = goodsService.findById(227L, "");
        log.info("byId2.viewCount={}", byId2.get(0).getViewCount());
    }
}