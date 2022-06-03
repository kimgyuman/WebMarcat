package marcat.goods.service;

import marcat.goods.dto.CreateGoodsDTO;
import marcat.goods.dto.SearchType;
import marcat.goods.dto.UpdateFileDTO;
import marcat.goods.dto.UpdateGoodsDTO;
import marcat.goods.vo.*;
import marcat.members.dto.JusoDTO;

import java.util.HashMap;
import java.util.List;

public interface GoodsService {

    //최근 Goods 6개 데이터
    List<Goods> latestSixGoods(String id);

    //Paging Goods 6개 데이터
    List<Goods> pagingGoods(SearchType searchType);

    //id로 찾아오기
    List<Goods> findById(Long id, String userId);

    //LastValue 찾아오기
    Goods lastValue(Long id);

    //Goods 정보
    Goods findOneGoods(Long Id, Long goodsId);

    //Goods에서 키워드로 찾은 물건 데이터
    List<Goods> findByKeyword(SearchType searchType);

    //상품등록
    void insertGoods(Goods goods);

    //등록한 상품에 이미지 등록
    void insertGoodsImage(GoodsImages goodsImages);

    // category 세팅을 위한 리스트 가져오기
    List<Categories> category_list();


    //Comments goodsId로 가져오기
    List<GoodsComments> findCommentsById(Long goodsId);

    //Images goodsId로 가져오기
    List<GoodsImages> findImagesById(Long goodsId);

    int checkWish(long memberId, long goodsId);

    // search juso
    List<JusoDTO> pullArea(String searchJuso);

    //memberId로 Goods List 가져오기
    List<Goods> findByMemberId(Long memberId);

    //deleteGoods
    void deleteGoodsById(Long id);

    //remakeGoods
    void remakeGoodsById(UpdateGoodsDTO updateGoodsDTO);

    void remakeFilebyId(UpdateFileDTO updateFileDTO);

    //delete img in db
    void deleteImgInDB(String savedName);

    //Comments memberId로 가져오기
    List<GoodsComments> findCommentsByMemberId(Long memberId);

    //comments goodsId로 가져오기
    List<GoodsComments> findCommentsByGoodsId(Long goodsId);
    //comments paging
    List<GoodsComments> pagingComments(HashMap<String,Object> hash);

    void createMarketComments(GoodsComments goodsComments);

    GoodsComments selectOneComments(Long commentsId);

    void updateMarketComments(HashMap<String, Object> hashMap);

    int deleteMarketComments(Long commentsId);

    void plusWish(WishList wishList);

    void deleteWish(Long memberId, Long goodsId);

    WishList findWish(Long memberId, Long goodsId);

    void sendReport(Report report);

    Report findOneReport(Long memberId, Long goodsId);

    void sendRequest(RequestBuy requestBuy);

    RequestBuy findRequest(Long memberId, Long goodsId);

    int viewCountUp(Long id);
}
