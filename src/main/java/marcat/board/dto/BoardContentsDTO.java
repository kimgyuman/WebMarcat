package marcat.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import marcat.goods.vo.KoreaArea;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
public class BoardContentsDTO {
    private Long id;
    private String nickname;
    private String address;
    private String title;
    private String contents;
    private String createTime;
    private Integer viewCount;
    private Integer wishCount;
    private String status;
    private Long memberId;
    private String admNm;
    private MemberImages memberImages;
    private boolean wishList;
    private boolean report;


    private List<String> boardImages;
    private Member member;
    private KoreaArea koreaArea;


    public BoardContentsDTO(long id, Long memberId, String admCd8, String title, String contents, Integer viewCount, LocalDateTime createTime, Integer wishCount, String status, String nickname, List<String> imageName, Member member, KoreaArea koreaArea, MemberImages memberImages, boolean wishList, boolean report) {
        this.id = id;
        this.memberId = memberId;
        this.address = admCd8;
        this.title = title;
        this.contents = contents;
        this.viewCount = viewCount;
        this.createTime =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(createTime);

        this.wishCount = wishCount;
        this.status = status;
        this.nickname = nickname;
        this.boardImages = imageName;
        this.member = member;
        this.koreaArea = koreaArea;
        this.memberImages = memberImages;
        this.wishList = wishList;
        this.report = report;
    }
//    private Integer reportCount;



}
