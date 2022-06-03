package marcat.board.vo;

import lombok.*;
import marcat.board.dto.BoardCommentDTO;
import marcat.board.dto.BoardUpdateDTO;

import java.sql.Timestamp;

@Getter
@ToString
@Setter
@AllArgsConstructor
public class BoardComments {

    private Long id;      // 댓글 seq(rno)
    private Long memberId;  // 댓글의 작성자
    private Long boardId;   // 댓글의 board값
    private String contents;
    private Timestamp createTime;

    private String memberPicture;

    private String admNm;
    private String nickname;

    private String nowUser;

    private Board board;

    public BoardComments() {
    }

    public BoardComments(BoardCommentDTO boardCommentDTO) {
        this.boardId = boardCommentDTO.getBoardId();
        this.memberId = boardCommentDTO.getMemberId();
        this.contents = boardCommentDTO.getContents();
    }

    public BoardComments(BoardUpdateDTO boardUpdateDTO) {
        this.boardId = boardUpdateDTO.getId();
        this.memberId = boardUpdateDTO.getMemberId();
        this.contents = boardUpdateDTO.getContents();
    }

    public BoardComments newBoardComments(BoardComments boardComments, String nowUser){
        BoardComments newBoardComments = new BoardComments();
        newBoardComments.id = boardComments.getId();
        newBoardComments.memberId = boardComments.getMemberId();
        newBoardComments.boardId = boardComments.getBoardId();
        newBoardComments.contents = boardComments.getContents();
        newBoardComments.createTime = boardComments.getCreateTime();
        newBoardComments.memberPicture = boardComments.getMemberPicture();
        newBoardComments.admNm = boardComments.getAdmNm();
        newBoardComments.nickname = boardComments.getNickname();
        newBoardComments.nowUser = nowUser;
        return  newBoardComments;
    }

}


