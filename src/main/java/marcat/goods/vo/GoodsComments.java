package marcat.goods.vo;

import lombok.Getter;
import lombok.ToString;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;

import java.time.LocalDateTime;

@Getter
@ToString
public class GoodsComments {

    private Long id;
    private Long memberId;
    private Long goodsId;
    private String contents;
    private LocalDateTime createTime;

    private Goods goods;
    private Member member;
    private MemberImages memberImages;

    public GoodsComments() {
    }

    public GoodsComments(Long memberId, Long goodsId, String contents, LocalDateTime createTime) {
        this.memberId = memberId;
        this.goodsId = goodsId;
        this.contents = contents;
        this.createTime = createTime;
    }
}
