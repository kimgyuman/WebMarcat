package marcat.goods.vo;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class GoodsImages {

    private Long id;
    private Long goodsId;
    private String originFileName;
    private String savedFileName;
    private LocalDateTime createTime;

    protected GoodsImages() {
    }

    public GoodsImages(Long goodsId, String originFileName, String savedFileName, LocalDateTime createTime) {
        this.goodsId = goodsId;
        this.originFileName = originFileName;
        this.savedFileName = savedFileName;
        this.createTime = createTime;
    }
}
