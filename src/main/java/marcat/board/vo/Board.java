package marcat.board.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import marcat.board.dto.BoardInsertDTO;
import marcat.board.dto.BoardUpdateDTO;
import marcat.goods.vo.KoreaArea;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
@Setter
public class Board {

    private Long id;
    private Long memberId;
    private String title;
    private String contents;
    private Integer viewCount;
    private LocalDateTime createTime;
    //
    private String status;
    private Integer wishCount;
    private String nickname;
    private String admCd8;
    private String admNm;
    private MemberImages memberImages;
    private BoardReport boardReport;

    private BoardImages boardImages;
    private Member member;
    private KoreaArea koreaArea;
    private BoardWishList boardWishList;


    protected Board() {
    }

    public Board(Long memberId) {
        this.memberId = memberId;
    }

    public Board(BoardInsertDTO boardInsertDTO) {   // insert에서 필요한것만 DTO에서 꺼내옴.
        this.memberId = boardInsertDTO.getMemberId();
        this.title = boardInsertDTO.getTitle();
        this.contents = boardInsertDTO.getContents();
        this.admCd8 = boardInsertDTO.getAdmCd8();
        this.nickname = boardInsertDTO.getNickname();
    }

    public Board(BoardUpdateDTO boardUpdateDTO) {
        this.id = boardUpdateDTO.getId();
        this.title = boardUpdateDTO.getTitle();
        this.contents = boardUpdateDTO.getContents();
        this.createTime = boardUpdateDTO.getCreate_time();
    }

    public Board(Long memberId , String title, String contents, String admCd8, String nickname) {
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
        this.admCd8 = admCd8;
        this.nickname= nickname;
    }
}
