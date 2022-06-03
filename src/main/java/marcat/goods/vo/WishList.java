package marcat.goods.vo;

import lombok.Getter;
import lombok.ToString;
import marcat.members.vo.Member;

import java.time.LocalDateTime;

@Getter
@ToString
public class WishList {

    private Long id;
    private Long memberId;
    private Long goodsId;
    private LocalDateTime wlCreateTime;

    private GoodsImages goodsImages;
    private Goods goods;
    private KoreaArea koreaArea;
    private Member member;

    public WishList() {}

    public WishList(Long memberId, Long goodsId, LocalDateTime wlCreateTime) {
        this.memberId = memberId;
        this.goodsId = goodsId;
        this.wlCreateTime = wlCreateTime;
    }
}
