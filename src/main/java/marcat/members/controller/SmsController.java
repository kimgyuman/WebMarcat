package marcat.members.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.members.dto.smsAuth.SmsDTO;
import marcat.members.service.MemberService;
import marcat.members.service.SmsService;
import marcat.members.dao.SmsAuthCodeMapDAO;
import marcat.members.vo.Member;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;
    private final SmsAuthCodeMapDAO smsAuthCodeMapDAO;
    private final MemberService memberService;

    // sms를 보내는 처리
    @PostMapping("/sms")
    public boolean smsCheck(@RequestBody HashMap<String , String > request) throws NoSuchAlgorithmException, URISyntaxException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException {
        if (request.isEmpty()) {
            return false;
        }
        boolean result = false;
        String phoneNum = request.get("recipientPhoneNumber").replaceAll(" ", "");
        String pattern = "^[0-9]{10,11}$";
        if (!phoneNum.matches(pattern)) {
            return false;
        }
        SmsDTO smsDTO = smsService.sendSms(phoneNum);
        if (smsDTO != null) {
            smsAuthCodeMapDAO.addElement(smsDTO.getRecipientPhoneNumber(), smsDTO.getRandomCode());
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    // 입력 받은 번호가 동일한지 확인 처리
    @PostMapping("/smscheck")
    public Map smsCheckResult(@RequestBody HashMap<String , String > request) {
        Map<String , String > result = new HashMap<>();
        if (request.isEmpty()) {
            result.put("result", "100"); // 입력된 전화번호가 없습니다.
            return result;
        }
        String recipientPhoneNumber = request.get("recipientPhoneNumber").replaceAll(" ", "");
        String authCode = request.get("authCode").replaceAll(" ", "");
        String phoneNum = request.get("recipientPhoneNumber").replaceAll(" ", "");
        String pattern = "^[0-9]{10,11}$";
        String codePattern = "^[0-9]{6}$";
        Member member = memberService.findByPhone(phoneNum);
        if (!phoneNum.matches(pattern)) {
            result.put("result", "300"); // 잘못된 전화번호 입니다.
            return result;
        } else if (!authCode.matches(codePattern)) {
            result.put("result", "310"); // 인증번호를 정확하게 입력하세요.
            return result;
        } else if (!smsAuthCodeMapDAO.checkElement(recipientPhoneNumber, Double.valueOf(authCode))) {
            result.put("result", "320"); // 인증번호가 다릅니다.
            return result;
        } else if (request.get("uId") != null && member != null && !member.getUId().equals(request.get("uId"))) {
            result.put("result", "400"); // 아이디가 존재하지 않습니다.
            return result;
        } else if (request.get("uId") != null && member != null && member.getName().equals("카카오유저")) {
            result.put("result", "210"); // 카카오 로그인유저입니다.
            return result;
        } else if (smsAuthCodeMapDAO.checkElement(recipientPhoneNumber, Double.valueOf(authCode))){
            result.put("result", "200");
            return result;
        } else {
            result.put("result", "500");
        }
        return result;
    }
}