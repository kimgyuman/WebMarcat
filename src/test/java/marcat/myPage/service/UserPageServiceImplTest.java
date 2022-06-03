package marcat.myPage.service;

import lombok.extern.slf4j.Slf4j;
import marcat.goods.dto.SearchType;
import marcat.goods.vo.Goods;
import marcat.goods.vo.GoodsComments;
import marcat.goods.vo.RequestBuy;
import marcat.goods.vo.WishList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
class UserPageServiceImplTest {

    @Autowired
    private UserPageService userPageService;

    @Test
    void pagingGoods() {
        SearchType searchType = new SearchType();
        searchType.setSellStatus("SELL");
        searchType.setStartNum("1");
        searchType.setId("29");
        log.info("searchType={}", searchType);
        List<Goods> goods = userPageService.pagingGoods(searchType);
        System.out.println("goods.size() = " + goods.size());
        System.out.println("goods = " + goods);
    }

    @Test
    void pagingRequestGoods() {
        SearchType searchType = new SearchType();
        searchType.setStartNum("1");
        searchType.setId("54");
        log.info("searchType={}", searchType);
        List<RequestBuy> goods = userPageService.pagingRequestGoods(searchType);
        System.out.println("goods.size() = " + goods.size());
        System.out.println("goods = " + goods);
    }

    @Test
    void pagingGoodsComment() {
        SearchType searchType = new SearchType();
        searchType.setStartNum("1");
        searchType.setId("54");
        log.info("searchType={}", searchType);
        List<GoodsComments> goodsComments = userPageService.pagingGoodsComment(searchType);
        System.out.println("goodsComments.size() = " + goodsComments.size());
        System.out.println("goodsComments = " + goodsComments);
    }

    @Test
    void agingMyRequestGoods() {
        SearchType searchType = new SearchType();
        searchType.setStartNum("1");
        searchType.setId("51");
        log.info("searchType={}", searchType);
        List<RequestBuy> requestBuys = userPageService.pagingMyRequestGoods(searchType);
        System.out.println("requestBuys.size() = " + requestBuys.size());
        System.out.println("requestBuys = " + requestBuys);
    }

    @Test
    void pagingMyWantedGoods() {
        SearchType searchType = new SearchType();
        searchType.setStartNum("1");
        searchType.setId("51");
        log.info("searchType={}", searchType);
        List<WishList> wishLists = userPageService.pagingMyWantedGoods(searchType);
        System.out.println("wishLists.size() = " + wishLists.size());
        System.out.println("wishLists = " + wishLists);
    }
}