package marcat.board.dto;


import lombok.Data;

@Data
public class BoardReportDTO {
    private Long id;
    private Long memberId;
    private Long boardId;
    private String contents;

    public BoardReportDTO() {
    }

    public BoardReportDTO(Long memberId, Long boardId, String contents) {
        this.memberId = memberId;
        this.boardId = boardId;
        this.contents = contents;
    }
}
