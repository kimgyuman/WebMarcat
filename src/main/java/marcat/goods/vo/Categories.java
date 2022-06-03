package marcat.goods.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Categories {

    private String id;
    private String name;

    protected Categories() {}

    public Categories(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
