package marcat.members.dto.smsAuth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MessagesDTO {
    private String to;
    private String content;
}
