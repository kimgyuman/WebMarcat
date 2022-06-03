package marcat.board.vo;

import lombok.Getter;
import lombok.ToString;
import marcat.goods.vo.KoreaArea;
import marcat.members.vo.Member;


import java.time.LocalDateTime;

@Getter
@ToString
public class BoardWishList {

    private Long id;
    private Long memberId;
    private Long boardId;
    private LocalDateTime bwlCreateTime;

    private BoardImages boardImages;
    private Board board;
    private KoreaArea koreaArea;
    private Member member;


    public BoardWishList(){
    }

    public BoardWishList(Long memberId, Long boardId, LocalDateTime bwlCreateTime) {
        this.memberId = memberId;
        this.boardId = boardId;
        this.bwlCreateTime = bwlCreateTime;
    }
}
