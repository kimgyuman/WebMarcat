package marcat.members.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.members.dao.KakaoMapDAO;
import marcat.members.dto.KakaoResponseDTO;
import marcat.members.dto.LoginDTO;
import marcat.members.service.MemberService;
import marcat.members.vo.Member;
import marcat.security.JwtAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final KakaoMapDAO kakaoMapDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    // 로그인 화면 이동
    @GetMapping("/login")
    public String loginMain(@ModelAttribute("loginForm") LoginDTO loginForm) {
        return "member/login/loginForm";
    }

    // 로그인
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginDTO loginForm, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "member/login/loginForm";
        }
        Member memberReuslt = memberService.findByUid(loginForm.getLoginId()); // 아이디로 멤버 조회

        // 아이디가 없는 경우 처리
        if (memberReuslt == null) {
            bindingResult.addError(new FieldError("loginForm", "loginId", "존재하지 않는 회원입니다."));
            return "member/login/loginForm";
        }

        // 비밀번호가 틀릴 경우 처리
        if (!passwordEncoder.matches(loginForm.getLoginPw(), memberReuslt.getPasswd())) {
            bindingResult.addError(new FieldError("loginForm", "loginPw", "잘못된 비밀번호입니다."));
            return "member/login/loginForm";
        }

        // 정지된 회원일 경우 처리
        if (memberReuslt.getActivated().equals("INACTIVE")) {
            bindingResult.addError(new FieldError("loginForm", "loginId", "정지된 아이디입니다."));
            return "member/login/loginForm";
        }

        // 토큰 생성하고 Cookie에 담아 전달
        createTokenCookie(memberReuslt.getRoleStatus(), String.valueOf(memberReuslt.getId()), response);
        return "redirect:/";
    }

    // 카카오 로그인 인가코드로 rest api 방식으로 kakao에 인증 받아서 유저 정보 가져오고 DB에서 유저가 가입한지를 확인
    @GetMapping("/login/kakao")
    public String loginKakaoMember(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, RedirectAttributes redirectAttributes) {
        String authCode = httpServletRequest.getParameter("code"); // 카카오에서 받아온 인가코드 파라미터
        KakaoResponseDTO kakaoMember = memberService.findKakaoMember(authCode); // 인가코드로 카카오로부터 유저 정보 조회

        // catch 에러 처리
        if (kakaoMember == null) {
            return "redirect:/login?status=110";
        }

        // 정지된 회원일 경우 처리
        if (kakaoMember.getActivated() != null && kakaoMember.getActivated().equals("INACTIVE")) {
            return "redirect:/login?login=fail";
        }

        if (kakaoMember.isSignUpStatus()) {
            // 유저가 DB에 있을 경우
            // 토큰 생성하고 Cookie에 담아 전달
            createTokenCookie(kakaoMember.getRole(), String.valueOf(kakaoMember.getMemberId()), httpServletResponse);
            return "redirect:/";
        } else {
            // 유저가 DB에 없을 경우
            // UUID를 생성하고 timelimit가 있는 map에 인가코드를 키로, uuid를 값으로 저장 후 uuid를 redirect로 클라이언트에 전달
            String uuid = UUID.randomUUID().toString();
            redirectAttributes.addAttribute("code", uuid);
            kakaoMapDAO.addElement(uuid, kakaoMember);
            return "redirect:/add/kakao";
        }
    }

    // jwt 토큰을 refresh해서 다시 발행
    @GetMapping("/refresh")
    public void refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        // httpServletRequest에서 토큰을 추출
        String token = jwtAuthenticationProvider.resolveToken(httpServletRequest);

        // 토큰이 존재하고, 토큰 기한이 유효할 경우
        if (token != null && jwtAuthenticationProvider.validateToken(token)) {
            Authentication authentication = jwtAuthenticationProvider.getAuthentication(token); // JWT 토큰에서 인증 정보 조회
            String userPk = jwtAuthenticationProvider.getUserPk(token); // JWT 토큰에서 유저 pk값 조회

            // 다시 토큰에 위 정보를 담아서 새로운 토큰 기한으로 전달
            createTokenCookie(String.valueOf(authentication.getAuthorities()), userPk, httpServletResponse);
        }
    }

    public void createTokenCookie(String role, String id, HttpServletResponse response) {
        // role을 List에 담아 jwt 토큰 생성
        List<String> roles = new ArrayList<>();
        roles.add(role);
        String token = jwtAuthenticationProvider.createToken(id, roles);

        // Cookie에 X-AUTH-TOKEN 이름으로 담아서 전송
        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/"); // Cookie 전송 번위 설정
        response.addCookie(cookie);
    }
}
