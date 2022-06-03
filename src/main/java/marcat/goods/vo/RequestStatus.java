package marcat.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RequestStatus {
    REQUEST("요청중"), DENIED("거절됨"), ALLOWED("수락됨");

    private String value;
}
