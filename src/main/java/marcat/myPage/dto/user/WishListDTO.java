package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.ToString;
import marcat.goods.vo.WishList;

import java.time.format.DateTimeFormatter;

@Getter
@ToString
public class WishListDTO {

    private final Long id;
    private final Long goodsId;
    private final String picture;
    private final String title;
    private final String sellStatus;
    private final String juso;
    private final String createTime;


    public WishListDTO(WishList wishList) {
        this.id = wishList.getId();
        this.goodsId = wishList.getGoodsId();
        this.picture = wishList.getGoodsImages().getSavedFileName();
        this.title = wishList.getGoods().getTitle();
        this.sellStatus = wishList.getGoods().getSellStatus();
        this.juso = wishList.getKoreaArea().getAdmNm();
        this.createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(wishList.getWlCreateTime());
    }
}
