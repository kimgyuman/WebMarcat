package marcat.goods.dto;

import lombok.Getter;
import lombok.Setter;
import marcat.goods.vo.Categories;
import marcat.goods.vo.GoodsImages;

import java.util.List;

@Getter
@Setter
public class RemakeGoods {
    private Long id;
    private List<GoodsImages> images;
    private Categories categories;
    private String title;
    private int price;
    private String content;
    private String negoState;

    public RemakeGoods() {}

    public RemakeGoods(Long id, List<GoodsImages> images, Categories categories, String title, int price, String content, String negoState) {
        this.id = id;
        this.images = images;
        this.categories = categories;
        this.title = title;
        this.price = price;
        this.content = content;
        this.negoState = negoState;
    }
}
