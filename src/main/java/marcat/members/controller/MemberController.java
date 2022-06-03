package marcat.members.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.aws.AwsS3Service;
import marcat.members.dao.KakaoMapDAO;
import marcat.members.dto.JusoDTO;
import marcat.members.dto.KakaoResponseDTO;
import marcat.members.dto.KakaoSignUpDTO;
import marcat.members.dto.SignUpDTO;
import marcat.members.service.MemberService;
import marcat.members.vo.Activated;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;
import marcat.members.vo.RoleStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final KakaoMapDAO kakaoMapDAO;
    private final AwsS3Service awsS3Service;
    private final ServletContext servletContext;

    // 일반 회원가입 화면 이동
    @GetMapping("/add")
    public String addMemberFormMain(@ModelAttribute("signUpDTO") SignUpDTO signUpDTO) {
        return "member/members/addMemberForm";
    }

    // 일반 회원가입 처리
    @PostMapping("/add")
    public String addMember(@Validated @ModelAttribute SignUpDTO signUpDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/members/addMemberForm";
        }

        // 같은 전화번호가 가입 되었는지 확인
        if (memberService.findByPhone(signUpDTO.getUserPhone()) != null) {
            bindingResult.addError(new FieldError("signUpDTO", "userPhone", "이미 가입된 회원입니다."));
            return "member/members/addMemberForm";
        }

        // 비밀번호 암호화 및 회원가입
        String encodePw = passwordEncoder.encode(signUpDTO.getUserPw());
        Member member = new Member(signUpDTO.getJuso(), signUpDTO.getUserId(), encodePw, signUpDTO.getUserName(), signUpDTO.getNickName(), signUpDTO.getUserPhone(), LocalDateTime.now(), RoleStatus.USER.getValue(), Activated.ACTIVITY.getValue(), null, null, null);
        boolean resultInsert = signUpMember(member);
        if (resultInsert) {
            return "redirect:/login";
        } else {
            return "member/members/addMemberForm?status=100";
        }
    }

    // 아이디 검색 화면으로 이동
    @GetMapping("/add/checkIdMain")
    public String checkIdMain() {
        return "member/members/checkId";
    }

    // ajax로 아이디 체크
    @PostMapping("/add/checkId")
    @ResponseBody
    public boolean checkId(@RequestBody HashMap<String, String> checkId) {

        // 공백, 지정한 글자 패턴인지 확인
        String pattern = "^[a-zA-Z0-9]{4,20}$";
        if (checkSpace(checkId.get("userId"))) {
            return true;
        } else if (!checkId.get("userId").matches(pattern)) {
            return true;
        }

        // 동일한 유저 아이디가 존재하는지 count로 확인하고 존재하면 false 반환
        boolean check = false;
        Member member = memberService.findByUid(checkId.get("userId"));
        if (member != null) {
            check = true;
        }

        return check;
    }

    // ajax로 닉네임 체크
    @PostMapping("/nick")
    @ResponseBody
    public boolean checkNickName(@RequestBody HashMap<String, String> nickName) {

        // 공백, 지정한 글자 패턴인지 확인
        String pattern = "^[a-zA-Z0-9ㄱ-ㅎ가-힣]{2,8}$";
        if (checkSpace(nickName.get("nickName"))) {
            return true;
        } else if (!nickName.get("nickName").matches(pattern)) {
            return true;
        }

        // sql count로 동일한 닉네임 있는지 확인 있으면 true 반환
        boolean result = false;
        String nick = nickName.get("nickName");
        if (memberService.checkNick(nick) == 1) result = true;
        return result;
    }

    // 주소 검색 화면으로 이동
    @GetMapping("/findJuso")
    public String findJusoMain() {
        return "member/members/juso";
    }

    // ajax로 주소 검색
    @PostMapping("/findJuso")
    @ResponseBody
    public List<JusoDTO> findJuso(@RequestBody HashMap<String, String> juso) {
        List<JusoDTO> jusos = null;

        // 공백, 지정한 글자 패턴인지 확인
        String pattern = "^[0-9ㄱ-ㅎ가-힣]{2,15}$";
        if (juso.get("juso") == null || juso.get("juso").isEmpty()) {
            return null;
        } else if (juso.get("juso").chars().allMatch(Character::isWhitespace)) {
            return null;
        } else if (juso.get("juso").matches(pattern)) {
            jusos = memberService.findJuso(juso.get("juso"));
        } else {
            return null;
        }
        return jusos;
    }

    // kakao유저 회원가입 화면으로 이동
    @GetMapping("/add/kakao")
    public String addKakaoMemberMain(@ModelAttribute("kakaoSignUpDTO") KakaoSignUpDTO kakaoSignUpDTO, @ModelAttribute("code") String uuid) {
        return "member/members/addKakaoForm";
    }

    // kakao유저 회원가입 처리
    @PostMapping("/add/kakaoSignUp")
    public String addKakaoMember(@Validated @ModelAttribute("kakaoSignUpDTO") KakaoSignUpDTO kakaoSignUpDTO, BindingResult bindingResult, @RequestParam("code") String uuid) {
        if (bindingResult.hasErrors()) {
            return "member/members/addKakaoForm";
        }

        // 동일한 핸드폰번호로 가입된 멤버가 있는지 확인
        if (memberService.findByPhone(kakaoSignUpDTO.getUserPhone()) != null) {
            bindingResult.addError(new FieldError("kakaoSignUpDTO", "userPhone", "이미 가입된 회원입니다."));
            return "member/members/addKakaoForm";
        }

        // kakaoMapDAO에 해당 uuid가 존재하는지 확인
        KakaoResponseDTO kakaoMember = kakaoMapDAO.getElement(uuid);
        // kakaoMapDAO에 존재하면 비밀번호 암호화 및 회원가입
        if (kakaoMember != null) {
            String encodePw = passwordEncoder.encode(kakaoMember.getId());
            Member member = new Member(kakaoSignUpDTO.getJuso(), kakaoMember.getId(), encodePw, "카카오유저", kakaoSignUpDTO.getNickName(), kakaoSignUpDTO.getUserPhone(), LocalDateTime.now(), RoleStatus.USER.getValue(), Activated.ACTIVITY.getValue(), kakaoMember.getAccessToken(), kakaoMember.getRefreshToken(), kakaoMember.getEmail());
            boolean resultInsert = signUpMember(member);
            if (resultInsert) {
                return "redirect:/login";
            } else {
                return "member/members/addKakaoForm?status=100";
            }
        } else {
            return "member/members/addKakaoForm";
        }
    }

    // 아이디 찾기 검색화면으로 이동
    @GetMapping("/findId")
    public String findIdMain(@ModelAttribute("signUpDTO") SignUpDTO signUpDTO) {
        return "member/login/findIdForm";
    }

    // 아이디 찾기 처리
    @PostMapping("/findId")
    @ResponseBody
    public Map findId(@RequestBody HashMap<String, String> userPhone) {
        Map<String, String> result = new HashMap<>();

        // 값이 비어 있는지 글자 패턴이 맞는지 확인
        if (userPhone.isEmpty()) {
            result.put("result", "100"); // 입력된 전화번호가 없습니다.
            return result;
        }
        String phoneNum = userPhone.get("userPhone").replaceAll(" ", ""); // 공백 제거
        String pattern = "^[0-9]{10,11}$";
        if (!phoneNum.matches(pattern)) {
            result.put("result", "300"); // 잘못된 전화번호 입니다.
            return result;
        }

        // 핸드폰 번호로 멤버 검색하고 카카오유저면 카카오에서 찾도록 반환
        try {
            Member member = memberService.findByPhone(userPhone.get("userPhone"));
            if (member.getName().equals("카카오유저")) {
                result.put("result", "210");
                return result;
            } else if (member != null) {
                result.put("result", member.getUId()); // 멤버가 존재하면 아이디를 담아서 반환
                return result;
            } else {
                result.put("result", "400"); // 존재하지 않는 회원입니다.
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "400");
            return result;
        }
    }

    // 비밀번호 찾기 검색화면으로 이동
    @GetMapping("/findPw")
    public String findPwMain(@ModelAttribute("signUpDTO") SignUpDTO signUpDTO) {
        return "member/login/findPwForm";
    }

    // 비밀번호 찾기 처리
    @PostMapping("/findPw")
    public String findPw(@ModelAttribute("signUpDTO") SignUpDTO signUpDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/login/findPwForm";
        }

        // 비밀번호 암호화 및 전화번호로 멤버 검색
        String encodePw = passwordEncoder.encode(signUpDTO.getUserPw());
        Member resultByphone = memberService.findByPhone(signUpDTO.getUserPhone());

        // 전화번호로 멤버가 있는지와 Client에서 받은 아이디가 멤버의 아이디와 동일 할 경우 update 실행
        if (resultByphone != null && signUpDTO.getUserId().equals(resultByphone.getUId())) {
            Member member = new Member(signUpDTO.getUserId(), encodePw, signUpDTO.getUserPhone());
            try {
                int result = memberService.updatePw(member);
                if (result == 1) {
                    return "redirect:/login";
                } else {
                    bindingResult.addError(new FieldError("signUpDTO", "userPw", "잘못된 비밀번호 입니다."));
                    return "member/login/findPwForm";
                }
            } catch (Exception e) {
                e.printStackTrace();
                bindingResult.addError(new FieldError("signUpDTO", "userPw", "정보를 다시 확인하시기 바랍니다."));
                return "member/login/findPwForm";
            }
        } else {
            bindingResult.addError(new FieldError("signUpDTO", "userPw", "정보를 다시 확인하시기 바랍니다."));
            return "member/login/findPwForm";
        }
    }

    // 회원가입 및 이미지 저장 메서드
    private boolean signUpMember(Member member) {
        boolean result = false;
        int resultNum = memberService.sigunUpMember(member);

        // 유저 아이디로 해당 유저가 DB에 저장 되었는지 확인하고 있을 경우 logo.png 이미지를 이미지 테이블에 저장
//        List<Member> resultMembers = memberService.findByUidJustMember(member.getUId());
        if (resultNum != 0 && member.getId() != null) {
            String realPath = servletContext.getRealPath("resources/img/logo.png"); // img 폴더의 Logo.png 파일 위치를 가져옴
            File file = new File(realPath);
            String directory = "img";
            String uploadFileName = awsS3Service.upload(file, directory);
            MemberImages memberImages = new MemberImages(member.getId(), "defaultImage", uploadFileName, LocalDateTime.now());
            int resultImgNum = memberService.insertMemImage(memberImages);

            // 이미지 테이블에 저장되지 않을 경우 아이디 삭제
            if (resultImgNum == 0) {
                memberService.delete(member.getId());
                return result;
            } else {
                result = true;
                return result;
            }
        } else {
            return result;
        }
    }

    // 글자 공란 있는지 확인하는 메서드
    public boolean checkSpace(String word) {
        boolean result = false;
        char[] arrCh = word.toCharArray();
        for (char c : arrCh) {
            if (Character.isWhitespace(c)) {
                result = true;
            }
        }
        if (word == null || word.isEmpty()) {
            result = true;
        } else if (word.chars().allMatch(Character::isWhitespace)) {
            result = true;
        }
        return result;
    }
}
