package marcat.members.vo;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
public class MemberImages {

    private Long id;
    private Long memberId;
    private String originFileName;
    private String savedFileName;
    private LocalDateTime createTime;

    protected MemberImages() {
    }

    public MemberImages(Long memberId, String originFileName, String savedFileName, LocalDateTime createTime) {
        this.memberId = memberId;
        this.originFileName = originFileName;
        this.savedFileName = savedFileName;
        this.createTime = createTime;
    }

    public MemberImages(Long id, Long memberId, String originFileName, String savedFileName, LocalDateTime createTime) {
        this.id = id;
        this.memberId = memberId;
        this.originFileName = originFileName;
        this.savedFileName = savedFileName;
        this.createTime = createTime;
    }
}
