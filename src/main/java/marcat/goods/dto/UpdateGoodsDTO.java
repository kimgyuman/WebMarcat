package marcat.goods.dto;

import lombok.*;

import java.util.HashMap;
import java.util.List;


@NoArgsConstructor
@ToString
@Getter
public class UpdateGoodsDTO {

    private Long id;
    private String title;
    private int price;
    private String contents;
    private String categoryId;
    private String negoStatus;

//        String stringPrice = (jsonResult.get("price").toString()).replace("\"", "");
//        int price = Integer.parseInt(stringPrice.replace("\"", ""));
//        String contents = (String.valueOf(jsonResult.get("contents"))).replace("\"", "");


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = (title.substring(title.lastIndexOf(",")+1)).replace("\"","");
    }

    public void setPrice(String price) {
//        this.price = Integer.parseInt(price.substring(price.lastIndexOf(",")+1));
        this.price = Integer.parseInt((price.substring(price.lastIndexOf(",")+1)).replace("\"",""));
    }


    public void setContents(String contents) {
        this.contents = contents.replace("\"","");
    }

    public void setCategory(String category) {
        this.categoryId = category.replace("\"","");
    }

    public void setNego(String nego) {
        this.negoStatus = nego.replace("\"","");
    }
}
