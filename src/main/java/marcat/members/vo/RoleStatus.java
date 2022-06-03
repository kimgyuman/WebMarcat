package marcat.members.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleStatus {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private String value;
}
