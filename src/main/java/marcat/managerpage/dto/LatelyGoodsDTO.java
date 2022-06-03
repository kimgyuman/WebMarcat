package marcat.managerpage.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class LatelyGoodsDTO {
    private String nickname;
    private String name;
    private Long goodsId;
    private String title;
    private int viewCount;
    private LocalDate createTime;
    private int price;
    private int wishCount;
}
