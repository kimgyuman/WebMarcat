package marcat.managerpage.dto;

import lombok.Getter;
import marcat.members.vo.Activated;

import java.time.LocalDate;

@Getter
public class MemberProfileDTO {
    private Long id;
    private String uId;
    private String name;
    private String nickname;
    private String phoneNum;
    private String addr;
    private LocalDate createTime;
    private Activated activated;
    private String roleStatus;
}
