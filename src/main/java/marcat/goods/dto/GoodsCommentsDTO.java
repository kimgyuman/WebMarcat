package marcat.goods.dto;

import lombok.Getter;
import lombok.ToString;
import marcat.goods.vo.Goods;
import marcat.goods.vo.GoodsComments;
import marcat.goods.vo.RequestBuy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
public class GoodsCommentsDTO {

    private Long id;
    private Long goodsId;
    private String title;
    private String contents;
    private String sellStatus;
    private String createTime;

    private Goods goods;

    public GoodsCommentsDTO() {
    }

    public GoodsCommentsDTO(GoodsComments goodsComments) {
        this.goodsId = goodsComments.getGoodsId();
        this.title = goodsComments.getGoods().getTitle();
        this.contents = goodsComments.getContents();
        this.sellStatus = goodsComments.getGoods().getSellStatus();

        this.createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(goodsComments.getCreateTime());
    }
}
