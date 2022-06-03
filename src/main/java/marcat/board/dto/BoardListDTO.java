package marcat.board.dto;


import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class BoardListDTO {
    private Long id;
    private Long memberId;
    private String nickname;
    private String title;
    private String contents;
    private int viewCount;
    private LocalDateTime createTime;
    private String status;



}
