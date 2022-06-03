package marcat.managerpage.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AddAdDTO {
    private Long adId;

    private int cateId;
    private String title;
    private String expiryTime;
    private String url;
    private String originFileName;
    private String savedFileName;

    public AddAdDTO() {}

}
