package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import marcat.goods.vo.RequestBuy;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
public class RequestGoodsDTO {
    private Long goodsId;
    private Long requestId;
    private String picture;
    private String title;
    private String sellStatus;
    private String juso;
    private Long requestMemberId;
    private String nick;
    private String createTime;
    private String requestStatus;

    public RequestGoodsDTO() {
    }

    public RequestGoodsDTO(RequestBuy requestBuy) {
        this.goodsId = requestBuy.getGoodsId();
        this.requestId = requestBuy.getId();
        this.requestStatus = requestBuy.getRequestStatus();
        this.picture = requestBuy.getGoodsImages().getSavedFileName();
        this.title = requestBuy.getGoods().getTitle();
        this.sellStatus = requestBuy.getGoods().getSellStatus();
        this.juso = requestBuy.getKoreaArea().getAdmNm();
        this.requestMemberId = requestBuy.getMemberId();
        this.nick = requestBuy.getMember().getNickName();

        this.createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(requestBuy.getRbCreateTime());
    }

}
