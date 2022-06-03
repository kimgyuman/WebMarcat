package marcat.managerpage.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MBoardListDTO {
    private Long id;
    private String nickname;
    private String title;
    private String contents;
    private int viewCount;
    private LocalDate createTime;
    private String status;
    private int wishCount;
}
