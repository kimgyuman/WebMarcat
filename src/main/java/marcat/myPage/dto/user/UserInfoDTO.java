package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class UserInfoDTO {

    @NotBlank(message = "공백 없이 입력하세요.")
    private final Long memberId;

    @NotBlank(message = "공백 없이 입력하세요.")
    private final String juso;

    @NotBlank(message = "공백 없이 입력하세요.")
    private final String userId;

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^[a-zA-Zㄱ-ㅎ가-힣]{2,10}$", message = "정확한 이름을 입력하세요.")
    private final String userName;

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^[a-zA-Z0-9ㄱ-ㅎ가-힣]{2,8}$", message = "특수문자를 제외한 2~8자리 닉네임을 입력하세요.")
    private final String nickName;

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "\"-\"없이 숫자만 입력하세요.")
    private final String userPhone;

    @NotBlank(message = "공백 없이 입력하세요.")
    private final String hanJuso;

    public UserInfoDTO(Long memberId, String juso, String userId, String userName, String nickName, String userPhone, String hanJuso) {
        this.memberId = memberId;
        this.juso = juso;
        this.userId = userId;
        this.userName = userName;
        this.nickName = nickName;
        this.userPhone = userPhone;
        this.hanJuso = hanJuso;
    }
}
