package marcat.members.dto;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@ToString
public class SignUpDTO {

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^[0-9]{8}$", message = "정확한 주소를 입력하세요.")
    private final String juso;

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "영문, 숫자 4~20자로 입력하세요.")
    private final String userId;

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "숫자+영문+특수문자 조합으로 8~15자리로 입력하세요.")
    private final String userPw;

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^[a-zA-Zㄱ-ㅎ가-힣]{2,10}$", message = "정확한 이름을 입력하세요.")
    private final String userName;

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^[a-zA-Z0-9ㄱ-ㅎ가-힣]{2,8}$", message = "특수문자를 제외한 2~8자리 닉네임을 입력하세요.")
    private final String nickName;

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "\"-\"없이 숫자만 입력하세요.")
    private final String userPhone;

    public SignUpDTO(String juso, String userId, String userPw, String userName, String nickName, String userPhone) {
        this.juso = juso;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.nickName = nickName;
        this.userPhone = userPhone;
    }
}
