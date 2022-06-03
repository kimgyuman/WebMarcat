package marcat.goods.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
public class SearchType {

        private String id;
    private String startNum;
    private String searchType;
    private String categories;
    private String koreaArea;
    private String keyword;
    private String sellStatus;
    private String viewStatus;

}
