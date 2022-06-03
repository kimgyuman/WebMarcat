package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.ToString;
import marcat.board.vo.Board;
import marcat.board.vo.BoardComments;

import java.sql.Timestamp;

@Getter
@ToString
public class MyBoardCommentDTO {
    private Long id;
    private Long boardId;
    private String title;
    private String contents;
    private Timestamp createTime;

    private String memberPicture;

    private String nickname;

    private Board board;

    public MyBoardCommentDTO(BoardComments boardComments) {
        this.boardId = boardComments.getBoardId();
        this.title = boardComments.getBoard().getTitle();
        this.contents = boardComments.getContents();
        this.createTime = Timestamp.valueOf(String.valueOf(Timestamp.valueOf(String.valueOf(boardComments.getCreateTime()))));
        this.memberPicture = boardComments.getMemberPicture();
        this.nickname = boardComments.getNickname();
    }
}
