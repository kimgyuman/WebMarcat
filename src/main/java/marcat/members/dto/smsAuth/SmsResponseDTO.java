package marcat.members.dto.smsAuth;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class SmsResponseDTO {
    private String requestId;
    private String statusCode;
    private String statusName;
}