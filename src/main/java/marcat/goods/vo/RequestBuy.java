package marcat.goods.vo;

import lombok.Getter;
import lombok.ToString;
import marcat.members.vo.Member;

import java.time.LocalDateTime;

@Getter
@ToString
public class RequestBuy {

    private Long id;
    private Long memberId;
    private Long goodsId;
    private String requestStatus;
    private LocalDateTime rbCreateTime;

    public RequestBuy() {}

    public RequestBuy(Long memberId, Long goodsId, String requestStatus) {
        this.memberId = memberId;
        this.goodsId = goodsId;
        this.requestStatus = requestStatus;
    }

    private GoodsImages goodsImages;
    private Goods goods;
    private KoreaArea koreaArea;
    private Member member;

}
