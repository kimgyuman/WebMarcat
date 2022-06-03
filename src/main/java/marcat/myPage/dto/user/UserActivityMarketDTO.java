package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserActivityMarketDTO {
        private Long memberId;
        private Long goodsId;
        private String title;
        private String viewCount;
        private String createTime;


}
