package marcat.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import marcat.board.vo.BoardImages;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class BoardUpdateDTO {
    private Long id;
    private String title;
    private Long memberId;
    private String contents;
    private LocalDateTime create_time;
    private List<BoardImages> images;

    private String admCd8;
    private String nickname;
    public BoardUpdateDTO() {

    }
    public void setId(Long id) {
        this.id = id;

    }

    public void setTitle(String title) {

        this.title = (title.substring(title.lastIndexOf(",")+1)).replace("\"","");
    }

    public void setContents(String contents) {
        this.contents = (contents.substring(contents.lastIndexOf(",")+1)).replace("\"","");
    }

    public BoardUpdateDTO(Long id, List<BoardImages> images, String title, String contents) {
        this.id = id;
        this.images = images;
        this.contents = contents;
        this.title = title;
    }

    public BoardUpdateDTO(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;

    }
}
