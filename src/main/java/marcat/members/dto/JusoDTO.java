package marcat.members.dto;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Getter
@ToString
public class JusoDTO {

    @NotBlank(message = "공백 없이 입력하세요.")
    @Pattern(regexp = "^[0-9ㄱ-ㅎ가-힣]{2,15}$", message = "특수문자를 제외한 한글을 입력하세요.")
    private String admNm;

    private String admCd8;

}
