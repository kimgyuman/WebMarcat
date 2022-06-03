package marcat.goods.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.goods.dto.SearchType;
import marcat.goods.dto.UpdateFileDTO;
import marcat.goods.dto.UpdateGoodsDTO;
import marcat.goods.vo.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class GoodsDAOImpl implements GoodsDAO {

    private final SqlSessionTemplate sqlSessionTemplate;
    private final String mapperLocation = "marcat.goods.dao.GoodsDAO.";

    @Override
    public List<Goods> latestSixGoods(String id) {
        HashMap<String , String > param = new HashMap<>();
        param.put("id", id);
        return sqlSessionTemplate.selectList(mapperLocation+"LatestSixGoods", param);
    }

    @Override
    public List<Goods> pagingGoods(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingGoods", searchType);
    }

    @Override
    public List<Goods> findById(Long id, String userId) {
        HashMap<String , String > param = new HashMap<>();
        param.put("goodsId", String.valueOf(id));
        param.put("memberId", userId);
        return sqlSessionTemplate.selectList(mapperLocation+"FindGoodsWithId",param);
    }

    @Override
    public Goods findLastGoods(Long id) {
        return sqlSessionTemplate.selectOne(mapperLocation+"lastValue", id);
    }

    @Override
    public List<Goods> findByKeyword(SearchType searchType) {
        return sqlSessionTemplate.selectList(mapperLocation+"FindGoodsByKeyword", searchType);
    }

    @Override
    public Long insertGoodsSet(Goods goods) {
        sqlSessionTemplate.insert(mapperLocation+"InsertGoods",goods);
        return goods.getId();
    }

    @Override
    public Long insertGoodsImage(GoodsImages goodsImages) {
        sqlSessionTemplate.insert(mapperLocation+"InsertImages",goodsImages);
        return goodsImages.getId();
    }


    @Override
    public List<Categories> categories_list() {
        return sqlSessionTemplate.selectList(mapperLocation+"categoriesList");
    }


    @Override
    public List<GoodsComments> findCommentsById(Long goodsId) {
        return sqlSessionTemplate.selectList(mapperLocation+"FindCommentsWithId", goodsId);
    }

    @Override
    public List<GoodsImages> findImagesById(Long goodsId) {
        return sqlSessionTemplate.selectList(mapperLocation+"FindImagesWithId", goodsId);
    }

    @Override
    public void deleteGoods(Long goodsId) {
        sqlSessionTemplate.delete(mapperLocation+"deleteGoods", goodsId);
    }

    @Override
    public void remakeGoodsById(UpdateGoodsDTO updateGoodsDTO) {
        sqlSessionTemplate.update(mapperLocation + "remakeGoods", updateGoodsDTO);
    }

    @Override
    public void remakeFileById(UpdateFileDTO updateFileDTO) {
        sqlSessionTemplate.update(mapperLocation + "remakeGoodsImage", updateFileDTO);
    }

    @Override
    public void deleteImg(String savedName) {
        sqlSessionTemplate.delete(mapperLocation + "fileDelete", savedName);
    }

    @Override
    public void plusWish(WishList wishList) {
        sqlSessionTemplate.insert(mapperLocation + "plusWish", wishList);
    }

    @Override
    public void deleteWish(Long memberId, Long goodsId) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("memberId", memberId);
        parameter.put("goodsId", goodsId);
        sqlSessionTemplate.delete(mapperLocation + "deleteWish", parameter);
    }

    @Override
    public WishList findWish(Long memberId, Long goodsId) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("memberId", memberId);
        parameter.put("goodsId", goodsId);
        return sqlSessionTemplate.selectOne(mapperLocation + "findWish", parameter);
    }


    @Override
    public int checkWish(long memberId, long goodsId) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("memberId", memberId);
        parameter.put("goodsId", goodsId);
        return sqlSessionTemplate.selectOne(mapperLocation + "DupleWishList", parameter);
    }

    @Override
    public List<Goods> findByMemberId(Long memberId) {
        return sqlSessionTemplate.selectList(mapperLocation+"FindGoodsWithMemberId", memberId);
    }

    @Override
    public List<GoodsComments> findCommentsByMemberId(Long memberId) {
        return sqlSessionTemplate.selectList(mapperLocation+"FindCommentsWithMemberId", memberId);
    }

    @Override
    public List<GoodsComments> findCommentsByGoodsId(Long goodsId) {
        return sqlSessionTemplate.selectList(mapperLocation+"FindCommentsWithId", goodsId);
    }

    @Override
    public List<GoodsComments> pagingComments(HashMap<String, Object> hash) {
        return sqlSessionTemplate.selectList(mapperLocation+"PagingGoodsComment", hash);
    }

    @Override
    public GoodsComments selectOneComments(Long commentsId) {
        return sqlSessionTemplate.selectOne(mapperLocation + "selectOneMarketComments", commentsId);
    }

    @Override
    public void sendReport(Report report) {
        sqlSessionTemplate.insert(mapperLocation + "sendReport", report);
    }

    @Override
    public Report findOneReport(Long memberId, Long goodsId ){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("memberId", memberId);
        hashMap.put("goodsId", goodsId);
        return sqlSessionTemplate.selectOne(mapperLocation + "oneReport", hashMap);
    }

    @Override
    public void deleteReport(Long memberId, Long goodsId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("memberId", memberId);
        hashMap.put("goodsId", goodsId);
        sqlSessionTemplate.delete(mapperLocation + "removeReport", hashMap);
    }

    @Override
    public void deleteComments(Long commentsId) {
        sqlSessionTemplate.delete(mapperLocation + "deleteMarketComments", commentsId);
    }

    @Override
    public void createMarketComments(GoodsComments goodsComments) {
        sqlSessionTemplate.insert(mapperLocation + "InsertComments", goodsComments);
    }

    @Override
    public void updateMarketComments(HashMap<String, Object> hashMap) {
        sqlSessionTemplate.update(mapperLocation + "updateMarketComments", hashMap);
    }

    @Override
    public void sendRequest(RequestBuy requestBuy) {
        sqlSessionTemplate.insert(mapperLocation + "sendRequest", requestBuy);
    }

    @Override
    public RequestBuy findRequest(Long memberId, Long goodsId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("memberId", memberId);
        hashMap.put("goodsId", goodsId);
        return sqlSessionTemplate.selectOne(mapperLocation + "findBuyRequest", hashMap);
    }

    @Override
    public int viewCountUp(Long id) {
        return sqlSessionTemplate.update(mapperLocation + "viewCountUp", id);
    }


}// -> service -> controller
