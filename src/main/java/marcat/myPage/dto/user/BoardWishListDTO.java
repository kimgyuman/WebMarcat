package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.ToString;
import marcat.board.vo.BoardWishList;

import java.time.format.DateTimeFormatter;

@Getter
@ToString
public class BoardWishListDTO {

    private final Long id;
    private final Long boardId;
    private final String picture;
    private final String title;
    private final String contents;
    private final String juso;
    private final String createTime;

    public BoardWishListDTO(BoardWishList boardWishList) {
        this.id = boardWishList.getId();
        this.boardId = boardWishList.getBoardId();
        this.picture = boardWishList.getBoardImages().getSavedFileName();
        this.title = boardWishList.getBoard().getTitle();
        this.contents = boardWishList.getBoard().getContents();
        this.juso = boardWishList.getKoreaArea().getAdmNm();
        this.createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(boardWishList.getBwlCreateTime());
    }
}
