package marcat.board.dto;

import lombok.*;
import marcat.board.vo.Board;

import java.time.format.DateTimeFormatter;



@Getter
@Setter
@ToString
public class BoardDTO {

    private Long id;
    private Long memberId;
    private String nickname;
    private String title;
    private String picture;
    private String contents;
    private int viewCount;
    private String createTime;
    private String status;
    private String boardImages;
    private String memberImages;
    private String admNm;
    private boolean boardWishList;
    private boolean nowUser;




    public BoardDTO(Board board) {
        this.id = board.getId();
//        this.memberId = board.getMemberId();
        this.picture = board.getBoardImages().getSavedFileName();
        this.nickname = board.getNickname();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.viewCount = board.getViewCount();
        this.nickname = board.getNickname();
        this.createTime =
//                String.valueOf(board.getCreateTime());
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(board.getCreateTime());
        this.status = board.getStatus();
        this.boardImages = board.getBoardImages().getSavedFileName();
        this.memberImages = board.getMemberImages().getSavedFileName();
        this.admNm = board.getAdmNm();

    }

    public BoardDTO() {
    }

    public BoardDTO MainBoard(Board board, boolean nowUser) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.id = board.getId();
        boardDTO.memberId = board.getMemberId();
        boardDTO.picture = board.getBoardImages().getSavedFileName();
        boardDTO.title = board.getTitle();
        boardDTO.contents = board.getContents();
        boardDTO.viewCount = board.getViewCount();
        boardDTO.createTime = String.valueOf(board.getCreateTime());
        boardDTO.status = board.getStatus();
        boardDTO.boardImages = board.getBoardImages().getSavedFileName();
        boardDTO.nickname = board.getNickname();
        boardDTO.memberImages = board.getMemberImages().getSavedFileName();
        boardDTO.admNm = board.getAdmNm();
        boardDTO.boardWishList = board.getBoardWishList().getId() != null;
        boardDTO.nowUser = nowUser;
        return boardDTO;
    }

    // 홈 메인에서 사용하는 메서드
    public BoardDTO getBoard(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.id = board.getId();
        boardDTO.memberId = board.getMemberId();
        boardDTO.title = board.getTitle();
        boardDTO.contents = board.getContents();
        boardDTO.createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(board.getCreateTime());
        boardDTO.boardImages = board.getBoardImages().getSavedFileName();
        boardDTO.nickname = board.getNickname();
        boardDTO.memberImages = board.getMemberImages().getSavedFileName();
        boardDTO.admNm = board.getAdmNm();
        boardDTO.boardWishList = board.getBoardWishList().getId() != null;
        return boardDTO;
    }



}
