package marcat.members.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class KakaoResponseDTO {

    private String id;
    private Long memberId;
    private String role;
    private String activated;
    private String email;
    private String nickName;
    private String accessToken;
    private String refreshToken;
    private String roleStatus;
    private boolean signUpStatus;

}