package marcat.board.vo;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class BoardImages {

    private Long id;
    private Long boardId;
    private String originFileName;
    private String savedFileName;
    private LocalDateTime createTime;

    public BoardImages() {
    }

    public BoardImages(Long boardId, String originFileName, String savedFileName, LocalDateTime createTime) {
        this.boardId = boardId;
        this.originFileName = originFileName;
        this.savedFileName = savedFileName;
        this.createTime = createTime;
    }
}
