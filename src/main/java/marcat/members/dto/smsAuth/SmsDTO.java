package marcat.members.dto.smsAuth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SmsDTO {
    private String recipientPhoneNumber;
    private Double randomCode;
}
