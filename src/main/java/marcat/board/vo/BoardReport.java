package marcat.board.vo;

import lombok.Getter;
import lombok.ToString;
import marcat.board.dto.BoardReportDTO;

@Getter
@ToString
public class BoardReport {

    private Long id;
    private Long memberId;
    private Long boardId;
    private String contents;

    public BoardReport() {
    }

    public BoardReport(Long memberId, Long boardId, String contents) {
        this.memberId = memberId;
        this.boardId = boardId;
        this.contents = contents;
    }

    public BoardReport(BoardReportDTO boardReportDTO) {
        this.id = boardReportDTO.getId();
        this.memberId = boardReportDTO.getMemberId();
        this.boardId = boardReportDTO.getBoardId();
        this.contents = boardReportDTO.getContents();
    }
}
