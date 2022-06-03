package marcat.myPage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.aws.AwsS3Service;
import marcat.board.dto.BoardCommentDTO;
import marcat.board.dto.BoardDTO;
import marcat.board.vo.Board;
import marcat.board.vo.BoardComments;
import marcat.board.vo.BoardWishList;
import marcat.goods.dto.GoodsCommentsDTO;
import marcat.goods.dto.GoodsDTO;
import marcat.goods.vo.*;
import marcat.members.dto.JusoDTO;
import marcat.members.dto.KakaoResponseDTO;
import marcat.members.service.KakaoLoginService;
import marcat.members.vo.Message;
import marcat.myPage.dto.user.*;
import marcat.goods.dto.SearchType;
import marcat.members.dto.SignUpDTO;
import marcat.members.service.MemberService;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;
import marcat.members.dao.KakaoMapDAO;

import marcat.myPage.service.UserPageService;
import marcat.security.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserInfoController {

    private final MemberService memberService;
    private final UserPageService userPageService;
    private final PasswordEncoder passwordEncoder;
    private final AwsS3Service profileService;
    private final KakaoLoginService kakaoLoginService;

    /* 마이페이지 메인화면 */
    @GetMapping
    public String userPageHomeMain(
            Principal principal, Model model) {
        SearchType searchType = new SearchType();
        searchType.setStartNum("1");
        searchType.setId(principal.getName());

        List<WishListDTO> wishGoodsList = new ArrayList<>();
        List<WishList> wishLists = userPageService.pagingMyWantedGoods(searchType);
        for (WishList r : wishLists) {
            WishListDTO wishListDTO = new WishListDTO(r);
            wishGoodsList.add(wishListDTO);
        }

        List<RequestGoodsDTO> requiredGoodsList = new ArrayList<>();
        List<RequestBuy> requestBuys = userPageService.pagingRequestGoods(searchType);
        for (RequestBuy r : requestBuys) {
            RequestGoodsDTO requestGoodsDTO = new RequestGoodsDTO(r);
            requiredGoodsList.add(requestGoodsDTO);
        }

        model.addAttribute("requiredGoodsList", requiredGoodsList);
        model.addAttribute("wishGoodsList", wishGoodsList);

        return "/myPage/user/userPage";
    }

    
    /* 유저 정보 view */
    @GetMapping("/userInfo")
    public String userPageUserInfoMain(
            Principal principal, Model model) {

        Member member = memberService.findById(Long.valueOf(principal.getName()));

        SignUpDTO signUpDTO = new SignUpDTO(member.getAdmCd8(),member.getUId(),null,member.getName(),member.getNickName(),member.getPhoneNum());

        model.addAttribute("signUpDTO", signUpDTO);
        model.addAttribute("jusoName", member.getKoreaArea().getAdmNm());

        if (!member.getName().equals("카카오유저")) { // 이름이 카카오유저가 아니면 일반유저폼
            return "/myPage/user/userPageUserInfo";
        } else {
            return "/myPage/user/userPageKakaoUserInfo"; // 카카오유저이면 카카오유저폼
        }
    }

    /* 유저 정보 수정 */
    @PostMapping("/userInfo")
    public String userPageUserInfo(
            @ModelAttribute("signUpDTO") SignUpDTO signUpDTO,
            BindingResult bindingResult,
            Principal principal, Model model) {

        Member preMember = memberService.findById(Long.valueOf(principal.getName()));

        if (bindingResult.hasErrors()) {
            if (!preMember.getName().equals("카카오유저")) { // 이름이 카카오유저가 아니면 일반유저폼
                return "/myPage/user/userPageUserInfo";
            } else {
                return "/myPage/user/userPageKakaoUserInfo"; // 카카오유저이면 카카오유저폼
            }
        }

        Member member = new Member(Long.valueOf(principal.getName()), signUpDTO.getJuso(),
                signUpDTO.getNickName(), signUpDTO.getUserPhone());

            try {
                int resultMember = userPageService.userModify(member);

                if (resultMember == 1) {
                    //굿즈닉네임 멤버닉네임으로 수정
                    Goods goods = new Goods(member.getId());
                    userPageService.updateGoodsNick(goods);

                    //보드닉네임 멤버닉네임으로 수정
                    Board board = new Board(member.getId());
                    userPageService.updateBoardNick(board);

                    return "redirect:/user/userInfo";

                } else {
                    bindingResult.addError(new FieldError("signUpDTO", "useNickName", "존재하는 닉네임입니다."));
                    if (!member.getName().equals("카카오유저")) { // 이름이 카카오유저가 아니면 일반유저폼
                        return "/myPage/user/userPageUserInfo";
                    } else {
                        return "/myPage/user/userPageKakaoUserInfo"; // 카카오유저이면 카카오유저폼
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (!member.getName().equals("카카오유저")) { // 이름이 카카오유저가 아니면 일반유저폼
                    return "/myPage/user/userPageUserInfo";
                } else {
                    return "/myPage/user/userPageKakaoUserInfo"; // 카카오유저이면 카카오유저폼
                }
            }

        }

    /* 비밀번호 변경 view */
    @GetMapping("/userPwChange")
    public String userPageUserPwChangeMain(
            @ModelAttribute("userPwUpdateDTO") UserPwUpdateDTO userPwUpdateDTO,
            BindingResult bindingResult, Principal principal, Model model) {

        return "/myPage/user/userPageUserPwChange";
    }

    /* 비밀번호 변경 */
    @PostMapping("/userPwChange")
    public String userPageUserPwChange(
            @ModelAttribute("userPwUpdateDTO") UserPwUpdateDTO userPwUpdateDTO,
            BindingResult bindingResult, Principal principal, Model model) {

        if (bindingResult.hasErrors()) {
            return "/myPage/user/userPageUserPwChange";
        }

        // 접속한 유저의 비밀번호 불러오기
        Member memberPw = memberService.findById(Long.valueOf(principal.getName()));

        //  비밀번호 입력한 비밀번호와 접속한 유저 비밀번호 비교 / 다르면 에러
        if (!passwordEncoder.matches(userPwUpdateDTO.getUserPw(), memberPw.getPasswd())) {
            bindingResult.addError(new FieldError("userPwUpdateDTO", "userPw", "현재 비밀번호가 일치하지 않습니다."));
            return "/myPage/user/userPageUserPwChange";
        }

        // 비밀번호 변경
        String encodePw = passwordEncoder.encode(userPwUpdateDTO.getUserPwUpdate());
        Member updatePwMember = new Member(Long.valueOf(principal.getName()), encodePw);
        userPageService.userModifyPw(updatePwMember);

        return "redirect:/user";
    }

    /* 프로필 이미지 view */
    @GetMapping("/userProfileUpdate")
    public String userPageProfileUpdateMain(
            Principal principal, Model model) {

        MemberImages memberImages = userPageService.findProfileImgById(Long.valueOf(principal.getName()));

        model.addAttribute("memberImages", memberImages);
        return "/myPage/user/userPageProfileUpdate";
    }

    /* 프로필 이미지 수정 */
    @PostMapping("/userProfileUpdate")
    public String userPageProfileUpdate(
            @RequestParam("file") MultipartFile file,
            MultipartHttpServletRequest request,
            Principal principal,Model model) throws IOException {

            MemberImages memImages = userPageService.findProfileImgById(Long.valueOf(principal.getName()));

            // 이전에 업로드된 이미지를 저장
            String deleteFileName = memImages.getSavedFileName();

            // S3에 저장된 이미지 삭제
            profileService.deleteFileInS3(deleteFileName);

            String originName = file.getOriginalFilename();//파일 이름
            String encodeName = profileService.upload(file, "img", 900, 900);//db에 들어갈 파일 이름,이미지 reSize
            String decodeName = null;

            try {
                decodeName = URLDecoder.decode(encodeName, "UTF-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            MemberImages memberImages = new MemberImages(memImages.getId(), memImages.getMemberId(), originName, decodeName , LocalDateTime.now());
            userPageService.userProfileImg(memberImages);
        return "/myPage/user/userPage";
    }

    /* 카카오톡 회원 탈퇴 */
        @GetMapping("/kakaoUserResign")
    public String userPageKakaoResignMain(
                @RequestParam("code") String uuid,
                Principal principal) {
            // 로그인한 유저 id 가져옴
            Member member = memberService.findById(Long.valueOf(principal.getName()));

            //uid 조회하여 탈퇴
            userPageService.kakaoUserDelete(member.getUId());

            return "redirect:/logout";
        }


    /* 회원 탈퇴 view */
    @GetMapping("/userResign")
    public String userPageUserResignMain(
            @ModelAttribute("userPwUpdateDTO") UserPwUpdateDTO userPwUpdateDTO,
            BindingResult bindingResult,Principal principal, Model model) {

        return "/myPage/user/userPageUserResign";
    }

    /* 회원 탈퇴 */
    @PostMapping("/userResign")
    public String userPageUserResign(@ModelAttribute("userPwUpdateDTO") UserPwUpdateDTO userPwUpdateDTO,
            BindingResult bindingResult, Principal principal ,Model model) {

        if (bindingResult.hasErrors()) {
            return "redirect:/user/userResign";
        }

        // 접속한 유저의 비밀번호 불러오기
        Member member = memberService.findById(Long.valueOf(principal.getName()));

        //  비밀번호 입력한 비밀번호와 접속한 유저 비밀번호 비교 / 다르면 에러
        if (!passwordEncoder.matches(userPwUpdateDTO.getUserPw(), member.getPasswd())) {
            bindingResult.addError(new FieldError("userPwUpdateDTO", "userPw", "현재 비밀번호가 일치하지 않습니다."));
            return "redirect:/user/userResign";
        }
        userPageService.userDelete(member);
        return "redirect:/logout";

    }




























}
