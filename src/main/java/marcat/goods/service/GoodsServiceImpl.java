package marcat.goods.service;

import lombok.RequiredArgsConstructor;
import marcat.goods.dao.GoodsDAO;
import marcat.goods.dto.CreateGoodsDTO;
import marcat.goods.dto.SearchType;
import marcat.goods.dto.UpdateFileDTO;
import marcat.goods.dto.UpdateGoodsDTO;
import marcat.goods.vo.*;
import marcat.members.dao.MemberDAO;
import marcat.members.dto.JusoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoodsServiceImpl implements GoodsService {

    private final GoodsDAO goodsDAO;
    private final MemberDAO memberDAO;

    @Override
    public List<Goods> latestSixGoods(String id) {
        return goodsDAO.latestSixGoods(id);
    }

    @Override
    public List<Goods> pagingGoods(SearchType searchType) {
        return goodsDAO.pagingGoods(searchType);
    }

    @Override
    public List<Goods> findById(Long id, String userId) {
        return goodsDAO.findById(id, userId);
    }

    @Override
    public Goods lastValue(Long id) {
        return goodsDAO.findLastGoods(id);
    }

    @Override
    public Goods findOneGoods(Long Id, Long goodsId) {
        return null;
    }

    @Override
    public List<Goods> findByKeyword(SearchType searchType) {
        goodsDAO.findByKeyword(searchType);
        return null;
    }

    @Override
    @Transactional
    public void insertGoods(Goods goods) {
        Long aLong = goodsDAO.insertGoodsSet(goods);
        System.out.println("aLong = " + aLong);
    }

    @Override
    @Transactional
    public void insertGoodsImage(GoodsImages goodsImages) {
        goodsDAO.insertGoodsImage(goodsImages);
    }

    @Override
    public List<Categories> category_list() {
        return goodsDAO.categories_list();
    }

    @Override
    public List<GoodsComments> findCommentsById(Long goodsId) {
        return goodsDAO.findCommentsById(goodsId);
    }

    @Override
    public List<GoodsImages> findImagesById(Long goodsId) {
        return goodsDAO.findImagesById(goodsId);
    }

    @Override
    public int checkWish(long memberId, long goodsId) {
        return goodsDAO.checkWish(memberId, goodsId);
    }

    @Override
    public List<JusoDTO> pullArea(String searchJuso) {
        return memberDAO.findJuso(searchJuso);
    }

    @Override
    public List<Goods> findByMemberId(Long memberId) {
        return goodsDAO.findByMemberId(memberId);
    }

    @Override
    @Transactional
    public void deleteGoodsById(Long id) {
        goodsDAO.deleteGoods(id);
    }

    @Override
    @Transactional
    public void remakeGoodsById(UpdateGoodsDTO updateGoodsDTO) {
        goodsDAO.remakeGoodsById(updateGoodsDTO);
    }

    @Override
    @Transactional
    public void remakeFilebyId(UpdateFileDTO updateFileDTO) {
        goodsDAO.remakeFileById(updateFileDTO);
    }

    @Override
    @Transactional
    public void deleteImgInDB(String savedName) {
        goodsDAO.deleteImg(savedName);
    }


    @Override
    public List<GoodsComments> findCommentsByMemberId(Long memberId) {
        return goodsDAO.findCommentsByMemberId(memberId);
    }

    @Override
    public List<GoodsComments> findCommentsByGoodsId(Long goodsId) {
        return goodsDAO.findCommentsByGoodsId(goodsId);
    }

    @Override
    public List<GoodsComments> pagingComments(HashMap<String, Object> hash) {
        return goodsDAO.pagingComments(hash);
    }

    @Override
    @Transactional
    public void createMarketComments(GoodsComments goodsComments) {
        goodsDAO.createMarketComments(goodsComments);
    }

    @Override
    public GoodsComments selectOneComments(Long commentsId) {
        return goodsDAO.selectOneComments(commentsId);
    }

    @Override
    @Transactional
    public void updateMarketComments(HashMap<String, Object> hashMap) {
        goodsDAO.updateMarketComments(hashMap);
    }


    @Override
    @Transactional
    public int deleteMarketComments(Long commentsId) {
        if (commentsId == null) {
            return 0;
        }else{
            goodsDAO.deleteComments(commentsId);
            return 1;
        }
    }

    @Override
    public void plusWish(WishList wishList) {
        goodsDAO.plusWish(wishList);
    }

    @Override
    public void deleteWish(Long memberId, Long goodsId) {
        goodsDAO.deleteWish(memberId, goodsId);
    }

    @Override
    public WishList findWish(Long memberId, Long goodsId) {
        return goodsDAO.findWish(memberId, goodsId);
    }

    @Override
    public void sendReport(Report report) {
        goodsDAO.sendReport(report);
    }

    @Override
    public Report findOneReport(Long memberId, Long goodsId) {
        return goodsDAO.findOneReport(memberId, goodsId);
    }

    @Override
    public void sendRequest(RequestBuy requestBuy) {
        goodsDAO.sendRequest(requestBuy);
    }

    @Override
    public RequestBuy findRequest(Long memberId, Long goodsId) {
        return goodsDAO.findRequest(memberId, goodsId);
    }

    @Override
    public int viewCountUp(Long id) {
        return goodsDAO.viewCountUp(id);
    }


}
