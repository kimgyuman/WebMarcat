package marcat.managerpage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import marcat.goods.vo.Commercial;
import marcat.managerpage.dto.*;

import java.util.List;

public interface ManagerPageService {


    //대시보드
    DayDTO findGoodsCntByDate();

    DayDTO findBoardCntByDate();

    List<LatelyGoodsDTO> findLatelyGoods();

    List<LatelyBoardDTO> findLatelyBoard();

    List<LatelyNotifyDTO> findLatelyGoodsNotify();

    List<LatelyNotifyDTO> findLatelyBoardNotify();


    //회원관리
    List<MemberManagementDTO> findMemberManagement(PageParam page);

    int memberCnt(PageParam page);

    //회원정보
    MemberProfileDTO findMemberProfile(Long id);

    MemberActivityDTO findMemberActivity(Long id);

    void sleepUser(Long id);

    void deleteUser(Long id);



    //차트
    List<CategoriesChartDTO> categoriesChart();

    MemberChartDTO memberChart();

    List<AreaChartDTO> areaChart(String area);



    //상품게시글 신고내역
    List<AllNotifyDTO> findAllGoodsNotify(PageParam page);

    void inactiveToActivation(Long id);

    void activationToInactive(Long id);

    int goodsNotifyCnt(PageParam page);

    List<NotifyViewDTO> goodsNotifyView(Long id);



    //게시글 신고내역
    List<AllNotifyDTO> findAllBoardNotify(PageParam page);

    void boardInactiveToActivation(Long id);

    void boardActivationToInactive(Long id);

    int boardNotifyCnt(PageParam page);

    List<NotifyViewDTO> boardNotifyView(Long id);




    //전체 상품 게시글
    List<GoodsListDTO> findGoodsList(PageParam page);

    int goodsCnt(PageParam page);


    //전체 게시글
    List<MBoardListDTO> findBoardList(PageParam page);

    int boardCnt(PageParam page);




    //광고관리
    List<AdDTO> findAdManagement(PageParam page);

    void addAd(Commercial commercial);

    Commercial findAd(Long id);

    void updateAd(Commercial commercial);

    void adActivation(List<Long> id);

    void adInactive(List<Long> id);

    void deleteAd(Long id);

    int adCnt(PageParam page);

    String findAdFileName(Long id);


    List<String> chartJsonData(List<?> dtoList) throws JsonProcessingException;

    List<AdViewDTO> adView();
}
