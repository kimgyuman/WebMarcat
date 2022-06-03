package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class UserProfileImgDTO {
    private Long id;
    private Long memberId;
    private String originFileName;
    private String savedFileName;
    private LocalDateTime createTime;


    public UserProfileImgDTO(){

    }

    public UserProfileImgDTO(Long id, Long memberId, String originFileName, String savedFileName, LocalDateTime createTime) {
        this.id = id;
        this.memberId = memberId;
        this.originFileName = originFileName;
        this.savedFileName = savedFileName;
        this.createTime = createTime;
    }
}
