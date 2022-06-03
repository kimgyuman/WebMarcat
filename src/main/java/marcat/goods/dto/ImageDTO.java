package marcat.goods.dto;

import marcat.goods.vo.GoodsImages;

import java.time.format.DateTimeFormatter;

public class ImageDTO {

    private final Long goodsId;
    private final String originName;
    private final String saveName;
    private final String createTime;

    public ImageDTO(GoodsImages goodsImages) {
        this.goodsId = goodsImages.getGoodsId();
        this.originName = goodsImages.getOriginFileName();
        this.saveName = goodsImages.getSavedFileName();
        this.createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(goodsImages.getCreateTime());
    }
}
