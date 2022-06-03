package marcat.managerpage.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberManagementDTO {
    private Long memberId;
    private String name;
    private String nickname;
    private String activated;
    private LocalDate createTime;
}
