package marcat.members.dto;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@ToString
public class KakaoSignUpDTO {

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^[0-9]{8}$", message = "정확한 주소를 입력하세요.")
    private final String juso;

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^[a-zA-Z0-9ㄱ-ㅎ가-힣]{2,8}$", message = "특수문자를 제외한 2~8자리 닉네임을 입력하세요.")
    private final String nickName;

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "\"-\"없이 숫자만 입력하세요.")
    private final String userPhone;

    public KakaoSignUpDTO(String juso, String userId, String nickName, String userPhone) {
        this.juso = juso;
        this.nickName = nickName;
        this.userPhone = userPhone;
    }
}
