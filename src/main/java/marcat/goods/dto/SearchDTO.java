package marcat.goods.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchDTO {
    private final String searchTitle;
    private final String word;
    private final String cateId;


    public SearchDTO(String searchTitle, String word, String cateId) {
        this.searchTitle = searchTitle;
        this.word = word;
        this.cateId = cateId;
    }
}
