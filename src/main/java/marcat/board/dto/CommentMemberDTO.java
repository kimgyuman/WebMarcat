package marcat.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentMemberDTO {
    private Long memberId;
    private String nickname;

    private String admNm;
    private LocalDateTime create_time;
    private String memberPicture;

    public CommentMemberDTO(Long memberId, String nickname, String admNm, LocalDateTime create_time, String memberPicture) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.admNm = admNm;
        this.create_time = create_time;
        this.memberPicture = memberPicture;
    }
}
