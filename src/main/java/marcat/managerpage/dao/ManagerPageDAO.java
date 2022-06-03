package marcat.managerpage.dao;

import marcat.goods.vo.Commercial;
import marcat.managerpage.dto.*;

import java.util.List;

public interface ManagerPageDAO {


    //대시보드
    DayDTO goodsByDate();

    DayDTO boardByDate();

    List<LatelyGoodsDTO> latelyGoods();

    List<LatelyBoardDTO> latelyBoard();

    List<LatelyNotifyDTO> latelyGoodsNotify();

    List<LatelyNotifyDTO> latelyBoardNotify();



    //회원관리
    List<MemberManagementDTO> memberManagement(PageParam page);

    int memberCnt(PageParam page);

    //회원 정보
    MemberProfileDTO memberProfile(Long id);

    MemberActivityDTO memberActivity(Long id);

    void sleepUser(Long id);

    void deleteUser(Long id);



    //차트
    List<CategoriesChartDTO> categoriesChart();

    MemberChartDTO memberChart();

    List<AreaChartDTO> areaChart(String area);



    //상품게시글 신고내역
    List<AllNotifyDTO> allGoodsNotify(PageParam page);

    void inactiveToActivation(Long id);

    void activationToInactive(Long id);

    int goodsNotifyCnt(PageParam page);

    List<NotifyViewDTO> goodsNotifyView(Long id);



    //게시글 신고내역
    List<AllNotifyDTO> allBoardNotify(PageParam page);

    void boardInactiveToActivation(Long id);

    void boardActivationToInactive(Long id);

    int boardNotifyCnt(PageParam page);

    List<NotifyViewDTO> boardNotifyView(Long id);



    //전체 상품 게시글
    List<GoodsListDTO> goodsList(PageParam page);

    int goodsCnt(PageParam page);


    //전체 게시글
    List<MBoardListDTO> boardList(PageParam page);

    int boardCnt(PageParam page);



    //광고관리
    List<AdDTO> adManagement(PageParam page);

    void addAd(Commercial commercial);

    Commercial findAd(Long id);

    void updateAd(Commercial commercial);

    void adActivation(Long id);

    void adInactive(Long id);

    void deleteAd(Long id);

    int adCnt(PageParam page);

    String adFileName(Long id);

    //광고 보기
    List<AdViewDTO> adView();

}