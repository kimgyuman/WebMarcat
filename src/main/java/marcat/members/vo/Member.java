package marcat.members.vo;

import lombok.Getter;
import lombok.ToString;
import marcat.goods.vo.KoreaArea;

import java.time.LocalDateTime;

@Getter
public class Member {

    private Long id;
    private String admCd8;
    private String uId;
    private String passwd;
    private String name;
    private String nickName;
    private String phoneNum;
    private LocalDateTime createTime;
    private String roleStatus;
    private String activated;
    private String accessToken;
    private String refreshToken;
    private String kakaoEmail;

    private MemberImages memberImages;
    private KoreaArea koreaArea;

    protected Member() {
    }

    public Member(String admCd8, String uId, String passwd, String name, String nickName, String phoneNum, LocalDateTime createTime, String roleStatus, String activated, String accessToken, String refreshToken, String kakaoEmail) {
        this.admCd8 = admCd8;
        this.uId = uId;
        this.passwd = passwd;
        this.name = name;
        this.nickName = nickName;
        this.phoneNum = phoneNum;
        this.createTime = createTime;
        this.roleStatus = roleStatus;
        this.activated = activated;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.kakaoEmail = kakaoEmail;
    }

    public Member(String uId, String passwd, String phoneNum) {
        this.uId = uId;
        this.passwd = passwd;
        this.phoneNum = phoneNum;
    }

    public Member(Long id, String admCd8, String uId, String nickName, String phoneNum) {
        this.id = id;
        this.admCd8 = admCd8;
        this.uId = uId;
        this.nickName = nickName;
        this.phoneNum = phoneNum;
    }

    public Member(Long id, String admCd8, String nickName, String phoneNum) {
        this.id = id;
        this.admCd8 = admCd8;
        this.nickName = nickName;
        this.phoneNum = phoneNum;
    }

    public Member(Long id, String passwd) {
        this.id = id;
        this.passwd = passwd;

    }
}
