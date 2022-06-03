package marcat.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import marcat.members.vo.Member;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BoardInsertDTO {
    private Long id;
    private Long memberId;
    private String title;
    private String contents;
    private Integer viewCount;
    private LocalDateTime createTime;
    private String status;
    private Integer wishCount;
    private String nickname;
    private String admCd8;

    public BoardInsertDTO(Member member, String title, String contents) {
        this.memberId = member.getId();
        this.title = title;
        this.contents = contents;
        this.admCd8 = member.getAdmCd8();
        this.nickname = member.getNickName();
    }

}
