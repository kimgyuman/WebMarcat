package marcat.managerpage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.aws.AwsS3Service;
import marcat.goods.vo.Commercial;
import marcat.managerpage.dto.*;
import marcat.managerpage.service.ManagerPageService;
import marcat.members.service.MemberService;
import marcat.members.vo.Activated;
import marcat.members.vo.Member;
import marcat.members.vo.RoleStatus;
import org.joda.time.format.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ManagerPageController {

    private final AwsS3Service awsS3Service;
    private final ManagerPageService managerPageService;

    //대시보드 매핑
    @GetMapping
    public String managerPage(Model model) {
        DayDTO goodsCnt = managerPageService.findGoodsCntByDate();
        DayDTO boardCnt = managerPageService.findBoardCntByDate();
        List<LatelyGoodsDTO> latelyGoods = managerPageService.findLatelyGoods();
        List<LatelyBoardDTO> latelyBoard = managerPageService.findLatelyBoard();
        List<LatelyNotifyDTO> latelyGoodsNotify = managerPageService.findLatelyGoodsNotify();
        List<LatelyNotifyDTO> latelyBoardNotify = managerPageService.findLatelyBoardNotify();

        model.addAttribute("goodsCnt",goodsCnt);
        model.addAttribute("boardCnt",boardCnt);
        model.addAttribute("latelyGoods",latelyGoods);
        model.addAttribute("latelyBoard",latelyBoard);
        model.addAttribute("latelyGoodsNotify", latelyGoodsNotify);
        model.addAttribute("latelyBoardNotify", latelyBoardNotify);

        return "/myPage/manager/managerPage";
    }



    //회원관리 매핑
    @GetMapping("/memberManagement")
    public String memberManagement(PageParam page, Model model) {
        List<MemberManagementDTO> memberDTO = managerPageService.findMemberManagement(page);
        int total = managerPageService.memberCnt(page);

        PageMaker pageMaker = new PageMaker(page,total);

        model.addAttribute("memberDTO",memberDTO);
        model.addAttribute("page",pageMaker);

        return "/myPage/manager/memberManagement";
    }

    //회원 정보 매핑
    @GetMapping("/memberProfile")
    public String memberProfile(@RequestParam Long id, Model model) {
        MemberProfileDTO profile = managerPageService.findMemberProfile(id);
        MemberActivityDTO activity = managerPageService.findMemberActivity(id);

        model.addAttribute("profile",profile);
        model.addAttribute("activity",activity);

        return "/myPage/manager/memberProfile";
    }

    //회원 비활성
    @GetMapping("/sleepUser")
    @ResponseBody
    public String sleepUser(@RequestParam Long id) {
        managerPageService.sleepUser(id);

        return "ok";
    }


    //회원 삭제
    @GetMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam Long id) {
        managerPageService.deleteUser(id);

        return "ok";
    }



    //차트매핑
    @GetMapping("/managerChart")
    public String managerChart() {
        return "/myPage/manager/managerChart";
    }

    //ajax 카테고리 등록량 차트
    @GetMapping("/categoriesChart")
    @ResponseBody
    public List<String> categoriesChart() throws JsonProcessingException {
        List<CategoriesChartDTO> categoriesChartDTOS = managerPageService.categoriesChart();
        List<String> jsonData = managerPageService.chartJsonData(categoriesChartDTOS);

        return jsonData;
    }

    //ajax 연간 회원 차트
    @GetMapping("/memberChart")
    @ResponseBody
    public MemberChartDTO memberChart() {
        MemberChartDTO memberChartDTO = managerPageService.memberChart();

        System.out.println("memberChartDTO = " + memberChartDTO);

        return memberChartDTO;
    }

    //ajax 지역별 카테고리 등록량 차트
    @GetMapping("/areaChart")
    @ResponseBody
    public List<String> areaChart(@RequestParam String area) throws JsonProcessingException {
        List<AreaChartDTO> areaChartDTOS = managerPageService.areaChart(area);
        List<String> jsonData = managerPageService.chartJsonData(areaChartDTOS);

        return jsonData;
    }



    //상품 게시글 신고내역 매핑
    @GetMapping("/notifyHistory")
    public String notifyHistory(Model model,PageParam page) {
        List<AllNotifyDTO> allGoodsNotify = managerPageService.findAllGoodsNotify(page);
        int total = managerPageService.goodsNotifyCnt(page);

        PageMaker pageMaker = new PageMaker(page,total);

        model.addAttribute("allGoodsNotify",allGoodsNotify);
        model.addAttribute("page",pageMaker);

        return "/myPage/manager/goodsNotifyHistory";
    }

    //상품 게시글 신고내역 활성/비활성
    @GetMapping("/notifyTableStatus")
    public String notifyTableStatus(@RequestParam Long id,
                                    @RequestParam String status) {
        if (status.equals("inactive") || status.equals("INACTIVE")) {
            managerPageService.inactiveToActivation(id);
        }else if (status.equals("activation") || status.equals("ACTIVATION")){
            managerPageService.activationToInactive(id);
        }
        return "redirect:/admin/notifyHistory";
    }

    //상품게시글 신고사유 뷰
    @GetMapping("/notifyContentsView")
    @ResponseBody
    public List<NotifyViewDTO> notifyContentsView(@RequestParam("id") Long id) {
        List<NotifyViewDTO> notifyViewDTOS = managerPageService.goodsNotifyView(id);
        return notifyViewDTOS;
    }



    //게시글 신고내역 매핑
    @GetMapping("/boardNotifyHistory")
    public String boardNotifyHistory(Model model,PageParam page) {
        List<AllNotifyDTO> allBoardNotify = managerPageService.findAllBoardNotify(page);
        int total = managerPageService.boardNotifyCnt(page);

        PageMaker pageMaker = new PageMaker(page,total);

        model.addAttribute("allBoardNotify", allBoardNotify);
        model.addAttribute("page",pageMaker);

        return "/myPage/manager/boardNotifyHistory";
    }

    //게시글 신고내역 활성/비활성
    @GetMapping("/notifyBoardStatus")
    public String notifyBoardStatus(@RequestParam Long id,
                                    @RequestParam String status) {
        if (status.equals("inactive") || status.equals("INACTIVE")) {
            managerPageService.boardInactiveToActivation(id);
        }else if (status.equals("activation") || status.equals("ACTIVATION")){
            managerPageService.boardActivationToInactive(id);
        }

        return "redirect:/admin/boardNotifyHistory";
    }

    //게시글 신고사유 뷰
    @GetMapping("/boardNotifyContentsView")
    @ResponseBody
    public List<NotifyViewDTO> boardNotifyContentsView(@RequestParam("id") Long id) {
        List<NotifyViewDTO> notifyViewDTOS = managerPageService.boardNotifyView(id);
        return notifyViewDTOS;
    }



    //광고관리 매핑
    @GetMapping("/adManagement")
    public String adManagement(Model model,PageParam page) {
        List<AdDTO> adManagement = managerPageService.findAdManagement(page);
        int total = managerPageService.adCnt(page);

        PageMaker pageMaker = new PageMaker(page,total);

        model.addAttribute("adManagement", adManagement);
        model.addAttribute("page",pageMaker);

        return "/myPage/manager/adManagement";
    }

    //광고 등록 매핑
    @GetMapping("/adRegistration")
    public String adRegistration(@ModelAttribute("addAdDTO") AddAdDTO addAdDTO, BindingResult bindingResult) {
        return "/myPage/manager/adRegistration";
    }

    //광고 등록 기능
    @PostMapping("/adRegistration")
    public String addAd(@ModelAttribute("addAdDTO") AddAdDTO addAdDTO,
                        @RequestParam("file") MultipartFile file,
                        BindingResult bindingResult) {

        String savedFileName = awsS3Service.upload(file, "ad", 600, 400);
        String decodeName = null;
        try {
            decodeName = URLDecoder.decode(savedFileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String expiryTime = addAdDTO.getExpiryTime()+" 00:00:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(expiryTime, formatter);

        Commercial commercial = new Commercial(addAdDTO.getCateId(),addAdDTO.getTitle(),localDateTime,file.getOriginalFilename(),decodeName,addAdDTO.getUrl());

        managerPageService.addAd(commercial);

        return "redirect:/admin/adManagement";
    }

    //광고 수정 매핑
    @GetMapping("/adUpdate")
    public String adUpdate(@RequestParam("adId") Long id,
                           @ModelAttribute("addAdDTO") AddAdDTO addAdDTO, BindingResult bindingResult,
                           Model model) {
        Commercial ad = managerPageService.findAd(id);

        model.addAttribute("ad",ad);
        return "/myPage/manager/adUpdate";
    }

    //광고 수정 기능
    @PostMapping("/adUpdate")
    public String updateAd(@ModelAttribute("addAdDTO") AddAdDTO addAdDTO, BindingResult bindingResult,
                           @RequestParam(value = "file" ,required = false) MultipartFile file,
                           @RequestParam("beforeOrigin") String beforeOrigin,
                           @RequestParam("beforeSaved") String beforeSaved,
                           @RequestParam("adId") Long id) {

        String expiryTime = addAdDTO.getExpiryTime()+" 00:00:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(expiryTime, formatter);

        if (file.isEmpty()) {
            Commercial commercial = new Commercial(id, addAdDTO.getCateId(), addAdDTO.getTitle(), localDateTime, beforeOrigin, beforeSaved, addAdDTO.getUrl());

            managerPageService.updateAd(commercial);
        } else {
            awsS3Service.deleteFileInS3(beforeSaved);

            String savedFileName = awsS3Service.upload(file, "ad", 600, 400);
            String decodeName = null;
            try {
                decodeName = URLDecoder.decode(savedFileName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            addAdDTO.setSavedFileName(decodeName);

            Commercial commercial = new Commercial(id, addAdDTO.getCateId(), addAdDTO.getTitle(), localDateTime, file.getOriginalFilename(), decodeName, addAdDTO.getUrl());

            managerPageService.updateAd(commercial);
        }

        return "redirect:/admin/adManagement";
    }

    //광고 활성
    @PostMapping("/adActivation")
    @ResponseBody
    public String adActivation(@RequestParam("id[]") List<Long> id,
                           @ModelAttribute("addAdDTO") AddAdDTO addAdDTO, BindingResult bindingResult) {
        managerPageService.adActivation(id);

        return "ok";
    }

    //광고 비활성
    @PostMapping("/adInactive")
    @ResponseBody
    public String adInactive(@RequestParam("id[]") List<Long> id,
                           @ModelAttribute("addAdDTO") AddAdDTO addAdDTO, BindingResult bindingResult) {
        managerPageService.adInactive(id);

        return "ok";
    }

    //광고 삭제
    @PostMapping("/deleteAd")
    @ResponseBody
    public String deleteAd(@ModelAttribute("addAdDTO") AddAdDTO addAdDTO, BindingResult bindingResult,@RequestParam("id[]") List<Long> id) {
        for (int i=0;i<id.size();i++) {
            String adFileName = managerPageService.findAdFileName(id.get(i));
            awsS3Service.deleteFileInS3(adFileName);
            managerPageService.deleteAd(id.get(i));
        }

        return "ok";
    }


    //전체 상품 게시글
    @GetMapping("/goodsList")
    public String goodsList(Model model,PageParam page) {
        List<GoodsListDTO> goodsList = managerPageService.findGoodsList(page);
        int total = managerPageService.goodsCnt(page);

        PageMaker pageMaker = new PageMaker(page,total);

        model.addAttribute("goodsList", goodsList);
        model.addAttribute("page",pageMaker);

        return "/myPage/manager/goodsList";
    }

    //전체 게시글
    @GetMapping("/boardList")
    public String boardList(Model model, PageParam page) {
        List<MBoardListDTO> boardList = managerPageService.findBoardList(page);
        int total = managerPageService.boardCnt(page);

        PageMaker pageMaker = new PageMaker(page,total);

        model.addAttribute("boardList",boardList);
        model.addAttribute("page",pageMaker);

        return "/myPage/manager/boardList";
    }

}
