package marcat.goods.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Goods {

    private Long id;
    private Long memberId;
    private String admCd8;
    private String categoryId;
    private String title;
    private String contents;
    private String sellStatus;
    private int viewCount;
    private LocalDateTime createTime;
    private int price;
    private String negoStatus;
    private int wishCount;
    private String status;
    private String nickName;

    private GoodsImages goodsImages;
    private Categories categories;
    private Member member;
    private KoreaArea koreaArea;
    private MemberImages memberImages;
    private WishList wishList;
    private Report report;
    private RequestBuy requestBuy;

    protected Goods() {}

    public Goods(Long memberId) {
        this.memberId = memberId;
    }

    public Goods(Long memberId, String admCd8, String categoryId, String title, String contents, String sellStatus, int viewCount, LocalDateTime createTime, int price, String negoStatus, int wishCount, String status, String nickName) {
        this.memberId = memberId;
        this.admCd8 = admCd8;
        this.categoryId = categoryId;
        this.title = title;
        this.contents = contents;
        this.sellStatus = sellStatus;
        this.viewCount = viewCount;
        this.createTime = createTime;
        this.price = price;
        this.negoStatus = negoStatus;
        this.wishCount = wishCount;
        this.status = status;
        this.nickName = nickName;
    }
}
