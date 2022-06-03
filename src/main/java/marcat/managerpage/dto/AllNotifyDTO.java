package marcat.managerpage.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AllNotifyDTO {
    private String nickname;
    private int cnt;
    private String id;
    private String title;
    private LocalDate createTime;
    private String status;
}
