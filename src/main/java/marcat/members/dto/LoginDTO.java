package marcat.members.dto;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;


@Getter
@ToString
public class LoginDTO {

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "영문, 숫자 4~20자로 입력하세요.")
    private final String loginId;

    //    It contains at least 8 characters and at most 15 characters.
//    It contains at least one lower case alphabet.
//    It contains at least one upper case alphabet.
//    It contains at least one digit.
//    It contains at least one special character i.e. !@#$%&*()-+=^.
//    It does not contain space.
    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "숫자+영문+특수문자(!@#$%^*+=-) 조합으로 8~15자리로 입력하세요.")
    private final String loginPw;

    public LoginDTO(String loginId, String loginPw) {
        this.loginId = loginId;
        this.loginPw = loginPw;
    }
}
