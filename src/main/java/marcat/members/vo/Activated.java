package marcat.members.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Activated {
    ACTIVITY("ACTIVITY"), INACTIVE("INACTIVE");

    private String value;
}
