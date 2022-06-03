package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.ToString;
import marcat.board.vo.Board;
import marcat.goods.vo.Goods;
import marcat.goods.vo.GoodsComments;
import marcat.members.vo.MemberImages;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
public class MyGoodsCommentDTO {
    private Long id;
    private Long goodsId;
    private String title;
    private String sellStatus;
    private String contents;
    private String createTime;

    private String memberPicture;

    private Goods goods;
    private MemberImages memberImages;

    public MyGoodsCommentDTO(GoodsComments goodsComments) {
        this.id = goodsComments.getId();
        this.goodsId = goodsComments.getGoodsId();
        this.title = goodsComments.getGoods().getTitle();
        this.contents = goodsComments.getContents();
        this.sellStatus = goodsComments.getGoods().getSellStatus();
        this.createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(goodsComments.getCreateTime());
        this.memberPicture = goodsComments.getMemberImages().getSavedFileName();
    }
}
