package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import marcat.members.vo.Member;
import marcat.members.vo.Message;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
public class MessageDTO {
    private Long msId;
    private Long targetId;
    private Long senderId;
    private String msMessage;
    private String msCreateTime;
    private String senderNick;
    private String targetNick;
    private String senderMemberImages;
    private String targetMemberImages;

    public MessageDTO(Message message) {
        this.msId = message.getMsId();
        this.targetId = message.getTargetId();
        this.senderId = message.getSenderId();
        this.msMessage = message.getMsMessage();
        this.senderNick = message.getMember().getNickName();
        this.senderMemberImages = message.getMemberImages().getSavedFileName();
        this.targetNick = message.getMember().getNickName();
        this.targetMemberImages = message.getMemberImages().getSavedFileName();
        this.msCreateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(message.getMsCreateTime());
    }
}
