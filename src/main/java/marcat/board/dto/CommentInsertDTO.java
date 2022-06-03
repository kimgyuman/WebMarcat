package marcat.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentInsertDTO {


    private Long id;
    private Long memberId;
    private Long boardId;
    private String contents;
    private LocalDateTime createTime;

    private String admCd8;
    private String nickname;
    private String memberPicture;


    public CommentInsertDTO() {

    }
}
