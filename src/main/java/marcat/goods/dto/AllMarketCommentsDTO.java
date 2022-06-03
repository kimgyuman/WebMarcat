package marcat.goods.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import marcat.goods.vo.Goods;
import marcat.goods.vo.GoodsComments;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
public class AllMarketCommentsDTO {
    private Long id;
    private Long memberId;
    private String nowUserId;
    private String memberPicture;
    private String memberNickname;
    private Long goodsId;
    private String contents;
    private String createTime;

    public AllMarketCommentsDTO(GoodsComments goodsComments, String nowUserId) {
        this.id = goodsComments.getId();
        this.memberId = goodsComments.getMemberId();
        this.nowUserId = nowUserId;
        this.memberPicture = goodsComments.getMemberImages().getSavedFileName();
        this.memberNickname = goodsComments.getMember().getNickName();
        this.goodsId = goodsComments.getGoodsId();
        this.contents = goodsComments.getContents();
        this.createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(goodsComments.getCreateTime());
    }
}
