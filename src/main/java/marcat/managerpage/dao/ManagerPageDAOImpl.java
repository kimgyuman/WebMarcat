package marcat.managerpage.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.goods.vo.Commercial;
import marcat.managerpage.dto.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class ManagerPageDAOImpl implements ManagerPageDAO {

    private final SqlSessionTemplate sqlSessionTemplate;
    private final String mapperLocation = "marcat.managerpage.dao.ManagerPageDAO.";


    //대시보드
    @Override
    public DayDTO goodsByDate() {
        return sqlSessionTemplate.selectOne(mapperLocation+"GoodsCntByDate");
    }

    @Override
    public DayDTO boardByDate() {
        return sqlSessionTemplate.selectOne(mapperLocation+"BoardCntByDate");
    }

    @Override
    public List<LatelyGoodsDTO> latelyGoods() {
        return sqlSessionTemplate.selectList(mapperLocation+"LatelyGoods");
    }

    @Override
    public List<LatelyBoardDTO> latelyBoard() {
        return sqlSessionTemplate.selectList(mapperLocation+"LatelyBoard");
    }

    @Override
    public List<LatelyNotifyDTO> latelyGoodsNotify() {
        return sqlSessionTemplate.selectList(mapperLocation+"LatelyGoodsNotify");
    }

    @Override
    public List<LatelyNotifyDTO> latelyBoardNotify() {
        return sqlSessionTemplate.selectList(mapperLocation+"LatelyBoardNotify");
    }



    //회원관리
    @Override
    public List<MemberManagementDTO> memberManagement(PageParam page) {
        return sqlSessionTemplate.selectList(mapperLocation+"MemberManagement",page);
    }

    @Override
    public int memberCnt(PageParam page) {
        return sqlSessionTemplate.selectOne(mapperLocation+"MemberCnt",page);
    }

    //회원정보
    @Override
    public MemberProfileDTO memberProfile(Long id) {
        return sqlSessionTemplate.selectOne(mapperLocation+"MemberProfile",id);
    }

    @Override
    public MemberActivityDTO memberActivity(Long id) {
        return sqlSessionTemplate.selectOne(mapperLocation+"MemberActivity",id);
    }

    @Override
    public void sleepUser(Long id) {
        sqlSessionTemplate.update(mapperLocation+"SleepUser",id);
    }

    @Override
    public void deleteUser(Long id) {
        sqlSessionTemplate.delete(mapperLocation+"MemberDelete",id);
    }


    //차트
    @Override
    public List<CategoriesChartDTO> categoriesChart() {
        return sqlSessionTemplate.selectList(mapperLocation+"CategoriesChart");
    }

    @Override
    public MemberChartDTO memberChart() {
        return sqlSessionTemplate.selectOne(mapperLocation+"MemberChart");
    }

    @Override
    public List<AreaChartDTO> areaChart(String area) {
        return sqlSessionTemplate.selectList(mapperLocation+"AreaChart",area);
    }



    //상품게시글 신고내역
    @Override
    public List<AllNotifyDTO> allGoodsNotify(PageParam page) {
        return sqlSessionTemplate.selectList(mapperLocation+"AllGoodsNotify",page);
    }

    @Override
    public void inactiveToActivation(Long id) {
        sqlSessionTemplate.update(mapperLocation+"InactiveToActivation",id);
    }

    @Override
    public void activationToInactive(Long id) {
        sqlSessionTemplate.update(mapperLocation+"ActivationToInactive",id);
    }

    @Override
    public int goodsNotifyCnt(PageParam page) {
        return sqlSessionTemplate.selectOne(mapperLocation+"GoodsNotifyCnt",page);
    }

    @Override
    public List<NotifyViewDTO> goodsNotifyView(Long id) {
        return sqlSessionTemplate.selectList(mapperLocation+"NotifyView",id);
    }


    //게시글 신고내역
    @Override
    public List<AllNotifyDTO> allBoardNotify(PageParam page) {
        return sqlSessionTemplate.selectList(mapperLocation+"AllBoardNotify",page);
    }

    @Override
    public void boardInactiveToActivation(Long id) {
        sqlSessionTemplate.update(mapperLocation+"BoardInactiveToActivation",id);
    }

    @Override
    public void boardActivationToInactive(Long id) {
        sqlSessionTemplate.update(mapperLocation+"BoardActivationToInactive",id);
    }

    @Override
    public int boardNotifyCnt(PageParam page) {
        return sqlSessionTemplate.selectOne(mapperLocation+"BoardNotifyCnt",page);
    }

    @Override
    public List<NotifyViewDTO> boardNotifyView(Long id) {
        return sqlSessionTemplate.selectList(mapperLocation+"BoardNotifyView",id);
    }


    //전체 상품 게시글
    @Override
    public List<GoodsListDTO> goodsList(PageParam page) {
        return sqlSessionTemplate.selectList(mapperLocation+"GoodsList",page);
    }

    @Override
    public int goodsCnt(PageParam page) {
        return sqlSessionTemplate.selectOne(mapperLocation+"GoodsCnt",page);
    }


    //전체 게시글
    @Override
    public List<MBoardListDTO> boardList(PageParam page) {
        return sqlSessionTemplate.selectList(mapperLocation+"BoardList",page);
    }

    @Override
    public int boardCnt(PageParam page) {
        return sqlSessionTemplate.selectOne(mapperLocation+"BoardCnt",page);
    }



    //광고관리
    @Override
    public List<AdDTO> adManagement(PageParam page) {
        return sqlSessionTemplate.selectList(mapperLocation+"AdManagement",page);
    }

    @Override
    public void addAd(Commercial commercial) {
        sqlSessionTemplate.insert(mapperLocation+"AddAd",commercial);
    }

    @Override
    public Commercial findAd(Long id) {
        return sqlSessionTemplate.selectOne(mapperLocation+"FindAd",id);
    }

    @Override
    public void updateAd(Commercial commercial) {
        sqlSessionTemplate.update(mapperLocation+"UpdateAd",commercial);
    }

    @Override
    public void adActivation(Long id) {
        sqlSessionTemplate.update(mapperLocation+"AdActivation",id);
    }

    @Override
    public void adInactive(Long id) {
        sqlSessionTemplate.update(mapperLocation+"AdInactive",id);
    }


    @Override
    public void deleteAd(Long id) {
        sqlSessionTemplate.delete(mapperLocation+"DeleteAd",id);
    }

    @Override
    public int adCnt(PageParam page) {
        return sqlSessionTemplate.selectOne(mapperLocation+"AdCnt",page);
    }

    @Override
    public String adFileName(Long id) {
        return sqlSessionTemplate.selectOne(mapperLocation+"AdFileName",id);
    }

    @Override
    public List<AdViewDTO> adView() {
        return sqlSessionTemplate.selectList(mapperLocation+"AdView");
    }
}