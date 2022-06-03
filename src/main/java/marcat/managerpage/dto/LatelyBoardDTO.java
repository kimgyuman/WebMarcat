package marcat.managerpage.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class LatelyBoardDTO {
    private String nickname;
    private String title;
    private Long boardId;
    private String viewCount;
    private LocalDate createTime;
}
