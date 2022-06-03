package marcat.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import marcat.board.vo.BoardComments;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class BoardCommentDTO {
    private Long id;      // 댓글 seq(rno)
    private Long memberId;  // 댓글의 작성자
    private Long boardId;   // 댓글의 board값
    private String contents;
    private Timestamp createTime;

    private String memberPicture;

    private String admNm;
    private String nickname;

    private String nowUser;

    public BoardCommentDTO() {
    }
    public BoardCommentDTO(BoardComments comment) {
        this.id = comment.getId();
        this.memberId = comment.getMemberId();
        this.boardId = comment.getBoardId();
        this.contents = comment.getContents();
        this.createTime = Timestamp.valueOf(String.valueOf(comment.getCreateTime()));
        this.memberPicture = comment.getMemberPicture();
        this.admNm = comment.getAdmNm();
        this.nickname = comment.getNickname();
        this.memberPicture = comment.getMemberPicture();
    }
    public BoardCommentDTO(BoardComments comment, String nowUser) {
        this.id = comment.getId();
        this.memberId = comment.getMemberId();
        this.boardId = comment.getBoardId();
        this.contents = comment.getContents();
        this.createTime = Timestamp.valueOf(String.valueOf(comment.getCreateTime()));
        this.memberPicture = comment.getMemberPicture();
        this.admNm = comment.getAdmNm();
        this.nickname = comment.getNickname();
        this.memberPicture = comment.getMemberPicture();
        this.nowUser = nowUser;
    }

}
