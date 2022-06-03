package marcat.goods.dto;

import lombok.Getter;

@Getter
public class LastOne {
    private Long goodsId;
    private String picture;
    private String title;
    private int price;
    private String sellStatus;
    private String juso;
    private Long nickId;
    private String nick;
    private String nickPicture;
    private String createTime;

    public LastOne() {}

    public LastOne(Long goodsId, String picture, String title,
                   int price, String sellStatus, String juso,
                   Long nickId, String nick, String nickPicture, String createTime) {

        this.goodsId = goodsId;
        this.picture = picture;
        this.title = title;
        this.price = price;
        this.sellStatus = sellStatus;
        this.juso = juso;
        this.nickId = nickId;
        this.nick = nick;
        this.nickPicture = nickPicture;
        this.createTime = createTime;
    }
}
