package marcat.goods.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFileDTO {
    private Long goodsId;
    private String originFileName;
    private String savedFileName;

    public UpdateFileDTO(Long goodsId, String originFileName, String savedFileName) {
        this.goodsId = goodsId;
        this.originFileName = originFileName;
        this.savedFileName = savedFileName;
    }
}
