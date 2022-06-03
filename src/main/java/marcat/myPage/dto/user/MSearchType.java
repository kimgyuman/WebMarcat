package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MSearchType {

        private Long msId;
        private String startNum;
        private String searchType;
        private String sellStatus;
        private String viewStatus;
        private String senderId;
        private String TargetId;
        private String senderNick;
        private String targetNick;
        private String senderMemberImages;
        private String targetMemberImages;

}
