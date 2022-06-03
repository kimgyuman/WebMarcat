package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class UserLinkDTO {
    private Long id;
    private String uId;
    private String nickName;
    private String addr;
    private String savedFileName;

    public UserLinkDTO() {
    }

}
