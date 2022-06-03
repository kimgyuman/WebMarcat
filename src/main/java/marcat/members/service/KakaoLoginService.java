package marcat.members.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import marcat.members.dto.KakaoResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@Component
public class KakaoLoginService {

    @Value("#{auth['kakao_client_id']}")
    private String clientId;

    @Value("#{auth['kakao_redirect_uri']}")
    private String redirectUri;

    @Value("#{auth['kakao_del_redirect_uri']}")
    private String delRedirectUri;

    public KakaoResponseDTO getUserInfo(String code) {
        // 인가코드로 AccessCode 받아오기
        KakaoResponseDTO accessToken = getAccessToken(code);

        // AccessCode로 유저 정보 받아오기
        KakaoResponseDTO kakaoDTO = getUserInfoByToken(accessToken);

        return kakaoDTO;
    }

    public KakaoResponseDTO getUserInfoUseDelete(String code) {
        // 인가코드로 AccessCode 받아오기
        KakaoResponseDTO accessToken = getAccessTokenUseDelete(code);

        // AccessCode로 유저 정보 받아오기
        KakaoResponseDTO kakaoDTO = getUserInfoByToken(accessToken);

        return kakaoDTO;
    }

    public KakaoResponseDTO getAccessToken(String code) {

        // HttpHeaders class를 통해 header에 파라미터 추가
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        // MultiVauleMap으로 body부분에 파라미터 추가
        MultiValueMap<String , String > bodyParams = new LinkedMultiValueMap<>();
        bodyParams.add("grant_type", "authorization_code");
        bodyParams.add("client_id", clientId);
        bodyParams.add("redirect_uri", redirectUri);
        bodyParams.add("code", code);

        // spring에서 지원하는 http 전송 class RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // header와 body파라미터를 가지고 HttpEntity class객체 생성
        HttpEntity<MultiValueMap<String , String>> kakaoAccessTokenRequest = new HttpEntity<>(bodyParams, httpHeaders);

        // restTemplate.exchange로 http 전송하고 ResponseEntity<String>으로 지정한 class로 반환받음
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(
                    "https://kauth.kakao.com/oauth/token",
                    HttpMethod.POST,
                    kakaoAccessTokenRequest,
                    String.class
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        KakaoResponseDTO kakaoDTO = createDto(response);

        return kakaoDTO;
    }

    public KakaoResponseDTO getAccessTokenUseDelete(String code) {

        // HttpHeaders class를 통해 header에 파라미터 추가
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        // MultiVauleMap으로 body부분에 파라미터 추가
        MultiValueMap<String , String > bodyParams = new LinkedMultiValueMap<>();
        bodyParams.add("grant_type", "authorization_code");
        bodyParams.add("client_id", clientId);
        bodyParams.add("redirect_uri", delRedirectUri);
        bodyParams.add("code", code);

        // spring에서 지원하는 http 전송 class RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // header와 body파라미터를 가지고 HttpEntity class객체 생성
        HttpEntity<MultiValueMap<String , String>> kakaoAccessTokenRequest = new HttpEntity<>(bodyParams, httpHeaders);

        // restTemplate.exchange로 http 전송하고 ResponseEntity<String>으로 지정한 class로 반환받음
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(
                    "https://kauth.kakao.com/oauth/token",
                    HttpMethod.POST,
                    kakaoAccessTokenRequest,
                    String.class
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        KakaoResponseDTO kakaoDTO = createDto(response);

        return kakaoDTO;
    }

    // 엑세스 코드로 유저 정보 가져오는 메서드
    public KakaoResponseDTO getUserInfoByToken(KakaoResponseDTO kakao) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + kakao.getAccessToken());
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String , String >> kakaoUserInfoRequest = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String resultBody = response.getBody();
        JsonNode jsonResult;
        try {
            jsonResult = objectMapper.readTree(resultBody);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String id = String.valueOf(jsonResult.get("id"));
        JsonNode kakao_account = jsonResult.get("kakao_account");
        kakao.setId(id);
        try {
            if (jsonResult.get("properties") != null && !String.valueOf(kakao_account.get("email")).isEmpty()) {
                kakao.setEmail(String.valueOf(kakao_account.get("email").asText()));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return kakao;
    }

    public KakaoResponseDTO createDto(ResponseEntity<String> response) {
        // jackson 지원 기능으로 string 문자열을 json으로 변환
        ObjectMapper mapper = new ObjectMapper();
        String resultBody = response.getBody();
        JsonNode jsonResult;
        try {
            jsonResult = mapper.readTree(resultBody);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // access_token을 String으로 가져오고 양 끝의 " " 를 제거하고 return
        String accessToken = String.valueOf(jsonResult.get("access_token").asText());
        String refreshToken = String.valueOf(jsonResult.get("refresh_token").asText());
        KakaoResponseDTO kakaoDTO = new KakaoResponseDTO();
        kakaoDTO.setAccessToken(accessToken);
        kakaoDTO.setRefreshToken(refreshToken);

        return kakaoDTO;
    }
}
