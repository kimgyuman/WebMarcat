package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Getter
@ToString
public class UserPwUpdateDTO {

    private final Long userId;

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "숫자+영문+특수문자 조합으로 8~15자리로 입력하세요.")
    private final String userPw;

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "숫자+영문+특수문자 조합으로 8~15자리로 입력하세요.")
    private final String userPwUpdate;



    public UserPwUpdateDTO(Long userId, String userPw, String userPwUpdate) {
        this.userId = userId;
        this.userPw = userPw;
        this.userPwUpdate = userPwUpdate;
    }
}
