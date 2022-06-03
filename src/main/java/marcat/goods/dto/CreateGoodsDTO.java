package marcat.goods.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import marcat.goods.vo.NegoStatus;
import org.springframework.lang.Nullable;

@Getter
@Setter
@ToString
public class CreateGoodsDTO {

    @Nullable
    private final String title;
    @Nullable
    private final int price;
    @Nullable
    private final String contents;
    private final String cateId;
    private final String negoStatus;


    public CreateGoodsDTO(String title, int price, String contents, String cateId, String negoStatus){
        this.title = title;
        this.price = price;
        this.contents = contents;
        this.cateId = cateId;
        this.negoStatus = negoStatus;
    }

}
