package marcat.managerpage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.goods.vo.Commercial;
import marcat.managerpage.dao.ManagerPageDAO;
import marcat.managerpage.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerPageServiceImpl implements ManagerPageService{

    private final ManagerPageDAO managerPageDAO;

    //대시보드
    @Override
    public DayDTO findGoodsCntByDate() {
        return managerPageDAO.goodsByDate();
    }

    @Override
    public DayDTO findBoardCntByDate() {
        return managerPageDAO.boardByDate();
    }

    @Override
    public List<LatelyGoodsDTO> findLatelyGoods() {
        return managerPageDAO.latelyGoods();
    }

    @Override
    public List<LatelyBoardDTO> findLatelyBoard() {
        return managerPageDAO.latelyBoard();
    }

    @Override
    public List<LatelyNotifyDTO> findLatelyGoodsNotify() {
        return managerPageDAO.latelyGoodsNotify();
    }

    @Override
    public List<LatelyNotifyDTO> findLatelyBoardNotify() {
        return managerPageDAO.latelyBoardNotify();
    }





    //회원관리
    @Override
    public List<MemberManagementDTO> findMemberManagement(PageParam page) {
        return managerPageDAO.memberManagement(page);
    }

    @Override
    public int memberCnt(PageParam page) {
        return managerPageDAO.memberCnt(page);
    }

    //회원정보
    @Override
    public MemberProfileDTO findMemberProfile(Long id) {
        return managerPageDAO.memberProfile(id);
    }

    @Override
    public MemberActivityDTO findMemberActivity(Long id) {
        return managerPageDAO.memberActivity(id);
    }

    @Override
    @Transactional
    public void sleepUser(Long id) {
        managerPageDAO.sleepUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        managerPageDAO.deleteUser(id);
    }

    @Override
    public List<CategoriesChartDTO> categoriesChart() {
        return managerPageDAO.categoriesChart();
    }

    @Override
    public MemberChartDTO memberChart() {
        return managerPageDAO.memberChart();
    }

    @Override
    public List<AreaChartDTO> areaChart(String area) {
        return managerPageDAO.areaChart(area);
    }


    //상품게시글 신고내역
    @Override
    public List<AllNotifyDTO> findAllGoodsNotify(PageParam page) {
        return managerPageDAO.allGoodsNotify(page);
    }

    @Override
    @Transactional
    public void inactiveToActivation(Long id) {
        managerPageDAO.inactiveToActivation(id);
    }

    @Override
    @Transactional
    public void activationToInactive(Long id) {
        managerPageDAO.activationToInactive(id);
    }

    @Override
    public int goodsNotifyCnt(PageParam page) {
        return managerPageDAO.goodsNotifyCnt(page);
    }

    @Override
    public List<NotifyViewDTO> goodsNotifyView(Long id) {
        return managerPageDAO.goodsNotifyView(id);
    }


    //게시글 신고내역
    @Override
    public List<AllNotifyDTO> findAllBoardNotify(PageParam page) {
        return managerPageDAO.allBoardNotify(page);
    }

    @Override
    @Transactional
    public void boardInactiveToActivation(Long id) {
        managerPageDAO.boardInactiveToActivation(id);
    }

    @Override
    @Transactional
    public void boardActivationToInactive(Long id) {
        managerPageDAO.boardActivationToInactive(id);
    }

    @Override
    public int boardNotifyCnt(PageParam page) {
        return managerPageDAO.boardNotifyCnt(page);
    }

    @Override
    public List<NotifyViewDTO> boardNotifyView(Long id) {
        return managerPageDAO.boardNotifyView(id);
    }


    //전체 상품 게시글
    @Override
    public List<GoodsListDTO> findGoodsList(PageParam page) {
        return managerPageDAO.goodsList(page);
    }

    @Override
    public int goodsCnt(PageParam page) {
        return managerPageDAO.goodsCnt(page);
    }



    //전체 게시글
    @Override
    public List<MBoardListDTO> findBoardList(PageParam page) {
        return managerPageDAO.boardList(page);
    }

    @Override
    public int boardCnt(PageParam page) {
        return managerPageDAO.boardCnt(page);
    }




    //광고 관리
    @Override
    public List<AdDTO> findAdManagement(PageParam page) {
        return managerPageDAO.adManagement(page);
    }

    @Override
    @Transactional
    public void addAd(Commercial commercial) {
        managerPageDAO.addAd(commercial);
    }

    @Override
    public Commercial findAd(Long id) {
        return managerPageDAO.findAd(id);
    }

    @Override
    @Transactional
    public void updateAd(Commercial commercial) {
        managerPageDAO.updateAd(commercial);
    }

    @Override
    @Transactional
    public void adActivation(List<Long> id) {
        for (int i=0;i<id.size();i++) {
            managerPageDAO.adActivation(id.get(i));
        }
    }

    @Override
    @Transactional
    public void adInactive(List<Long> id) {
        for (int i=0;i<id.size();i++) {
            managerPageDAO.adInactive(id.get(i));
        }
    }


    @Override
    @Transactional
    public void deleteAd(Long id) {
        managerPageDAO.deleteAd(id);
    }

    @Override
    public int adCnt(PageParam page) {
        return managerPageDAO.adCnt(page);
    }

    @Override
    public String findAdFileName(Long id) {
        return managerPageDAO.adFileName(id);
    }



    //json
    @Override
    public List<String> chartJsonData(List<?> dtoList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> list = new ArrayList<>();
        for (int i =0; i< dtoList.size();i++) {
            String json = mapper.writeValueAsString(dtoList.get(i));
            list.add(json);
        }
        return list;
    }

    @Override
    public List<AdViewDTO> adView() {
        return managerPageDAO.adView();
    }
}
