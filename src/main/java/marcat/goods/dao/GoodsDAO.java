package marcat.goods.dao;


import marcat.goods.dto.SearchType;
import marcat.goods.dto.UpdateFileDTO;
import marcat.goods.dto.UpdateGoodsDTO;
import marcat.goods.vo.*;

import java.util.HashMap;
import java.util.List;

public interface GoodsDAO {



    //최근 Goods 6개 데이터
    List<Goods> latestSixGoods(String id);

    //Paging Goods 6개 데이터
    List<Goods> pagingGoods(SearchType searchType);

    //id로 찾아오기
    List<Goods> findById(Long id, String userId);


    //마지막 게시글 찾아오기
    Goods findLastGoods(Long id);

    //Goods에서 키워드로 찾은 물건 데이터
    List<Goods> findByKeyword(SearchType searchType);

    //Goods에 데이터를 넣기 위한 세팅
    Long insertGoodsSet(Goods goods);

    //GoodsImage
    Long insertGoodsImage(GoodsImages goodsImages);

    //카테고리 리스트 정보
    List<Categories> categories_list();

    //Comments goodsId로 가져오기
    List<GoodsComments> findCommentsById(Long goodsId);

    //Images goodsId로 가져오기
    List<GoodsImages> findImagesById(Long goodsId);

    //delete Goods
    void deleteGoods(Long goodsId);

    //remake goods
    void remakeGoodsById(UpdateGoodsDTO updateGoodsDTO);

    void remakeFileById(UpdateFileDTO updateFileDTO);

    //delete imgs in db
    void deleteImg(String savedName);

    //wishList insert
    void plusWish(WishList wishList);
    //delete wish
    void deleteWish(Long memberId, Long goodsId);
    //findWish in goods
    WishList findWish(Long memberId, Long goodsId);
    // whishList member의 중복 확인
    int checkWish(long memberId, long goodsId);

    //memberId로 Goods List 가져오기
    List<Goods> findByMemberId(Long memberId);

    //Comments memberId로 가져오기
    List<GoodsComments> findCommentsByMemberId(Long memberId);

    //comments goodsId 로 가져오기
    List<GoodsComments> findCommentsByGoodsId(Long goodsId);

    //comments paging
    List<GoodsComments> pagingComments(HashMap<String,Object> hash);

    //댓글 하나
    GoodsComments selectOneComments(Long commentsId);

    //sendReport
    void sendReport(Report report);

    Report findOneReport(Long memberId, Long goodsId);

    void deleteReport(Long memberId, Long goodsId);

    //comments delete
    void deleteComments(Long commentsId);

    //Comments 만들기
    void createMarketComments(GoodsComments goodsComments);

    //comments 수정
    void updateMarketComments(HashMap<String, Object> hashMap);

    //거래 요청하기
    void sendRequest(RequestBuy requestBuy);

    //거래 확인데이터
    RequestBuy findRequest(Long memberId, Long goodsId);

    int viewCountUp(Long id);
}
