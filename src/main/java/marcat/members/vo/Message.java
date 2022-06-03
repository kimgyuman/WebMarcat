package marcat.members.vo;

import lombok.Getter;
import lombok.ToString;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;

import java.time.LocalDateTime;

@Getter
public class Message {

    private Long msId;
    private Long targetId;
    private Long senderId;
    private String msMessage;
    private LocalDateTime msCreateTime;

    private Member member;
    private MemberImages memberImages;

    protected Message() {
    }

    public Message(Long targetId, Long senderId, String msMessage, LocalDateTime msCreateTime) {
        this.targetId = targetId;
        this.senderId = senderId;
        this.msMessage = msMessage;
        this.msCreateTime = msCreateTime;
    }
}
