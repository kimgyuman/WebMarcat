package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SendMemberDTO {
    private Long msId;
    private Long targetId;
    private Long senderId;
    private String msMessage;
    private String msCreateTime;
    private String senderNick;
    private String targetNick;
    private String senderMemberImages;
    private String targetMemberImages;
}
