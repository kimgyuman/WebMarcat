package marcat.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NegoStatus {
    YES("YES"), NO("NO");

    private String value;
}
