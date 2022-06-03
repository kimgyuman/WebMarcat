package marcat.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SellStatus {
    SELL("SELL"), SELLING("SELLING"), SOLD("SOLD");

    private String value;
}
