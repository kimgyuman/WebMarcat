package marcat.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ViewStatus {
    ACTIVATION("ACTIVATION"), INACTIVE("INACTIVE");

    private String value;
}
