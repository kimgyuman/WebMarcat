package marcat.members.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.members.dto.smsAuth.MessagesDTO;
import marcat.members.dto.smsAuth.SmsDTO;
import marcat.members.dto.smsAuth.SmsRequestDTO;
import marcat.members.dto.smsAuth.SmsResponseDTO;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SmsService {

    @Value("#{auth['serviceId']}")
    private String serviceId;
    @Value("#{auth['accessKey']}")
    private String accessKey;
    @Value("#{auth['secretKey']}")
    private String secretKey;
    @Value("#{auth['phoneNum']}")
    private String fromPhone;


    // 6자리 랜덤 인증 숫자 문자 메시지 전송과 ExpiringMap에 전화번호를 key, 값을 인증번호로 저장
    public SmsDTO sendSms(String recipientPhoneNumber) throws JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {
        SmsDTO smsDTO = new SmsDTO();
        Long time = System.currentTimeMillis();
        List<MessagesDTO> messages = new ArrayList<>();
        int authNum = (int)(Math.random() * (999999 - 100000 + 1)) + 100000;
        messages.add(new MessagesDTO(recipientPhoneNumber, String.valueOf(authNum)));

        SmsRequestDTO smsRequest = new SmsRequestDTO("SMS", "COMM", "82", fromPhone, "내용", messages);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(smsRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", this.accessKey);
        String sig = makeSignature(time); // 암호화 및 시그니쳐 생성
        headers.set("x-ncp-apigw-signature-v2", sig);

        HttpEntity<String> body = new HttpEntity<>(jsonBody,headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SmsResponseDTO> smsResponse;
        try {
            smsResponse = restTemplate.exchange(
                    "https://sens.apigw.ntruss.com/sms/v2/services/"+this.serviceId+"/messages",
                    HttpMethod.POST,
                    body,
                    SmsResponseDTO.class
            );

            if(smsResponse.getBody().getStatusCode().equals("202")) {
                smsDTO.setRecipientPhoneNumber(recipientPhoneNumber);
                smsDTO.setRandomCode(Double.valueOf(authNum));
                return smsDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String makeSignature(Long time) throws NoSuchAlgorithmException, InvalidKeyException {

        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/"+ this.serviceId+"/messages";
        String timestamp = time.toString();
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        // SecretKeySpec으로 비밀키를 지정
        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        // HMACSHA256 는 SHA-256 해시 함수에서 생성되고 해시 기반 HMAC(메시지 인증 코드)로 사용되는 키 해시 알고리즘의 유형
        Mac mac = Mac.getInstance("HmacSHA256"); // 메시지 인증 코드(MAC)알고리즘 비밀키를 공유하는 사이에 사용됨
        mac.init(signingKey); // 지정된 키와 알고리즘 파라미터를 사용해 Mac 객체를 초기화

        byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8)); //지정된 바이트의 배열을 처리해 MAC 조작을 종료
        String encodeBase64String = Base64.encodeBase64String(rawHmac); // Base64 Encoding은 Binary Data를 Text로(공통 ASCII 영역의 문자로만 이루어진 문자열) 변경하는 Encoding

        return encodeBase64String;
    }
}
