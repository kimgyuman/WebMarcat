package marcat.goods.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Report {

    private Long id;
    private Long memberId;
    private Long goodsId;
    private String contents;

    public Report() {}

    public Report(Long memberId, Long goodsId, String contents) {
        this.memberId = memberId;
        this.goodsId = goodsId;
        this.contents = contents;
    }
}
