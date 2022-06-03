package marcat.goods.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import marcat.goods.vo.Goods;
import marcat.goods.vo.WishList;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@ToString
public class GoodsDTO {
    private Long goodsId;
    private boolean memberId;
    private String picture;
    private String title;
    private int price;
    private String sellStatus;
    private String juso;
    private Long nickId;
    private String nick;
    private String nickPicture;
    private String createTime;

    private boolean wishList;

    public GoodsDTO() {
    }

    public GoodsDTO(Goods goods) {
        this.goodsId = goods.getId();
        this.picture = goods.getGoodsImages().getSavedFileName();
        this.title = goods.getTitle();
        this.price = goods.getPrice();
        this.sellStatus = goods.getSellStatus();
        this.juso = goods.getKoreaArea().getAdmNm();
        this.nickId = goods.getMemberId();
        this.nick = goods.getNickName();
        this.nickPicture = goods.getMemberImages().getSavedFileName();
        this.createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(goods.getCreateTime());
    }

    public GoodsDTO getGoodsDTO(Goods goods) {
        GoodsDTO goodsDTO = new GoodsDTO();
        goodsDTO.goodsId = goods.getId();
        goodsDTO.picture = goods.getGoodsImages().getSavedFileName();
        goodsDTO.title = goods.getTitle();
        goodsDTO.price = goods.getPrice();
        goodsDTO.sellStatus = goods.getSellStatus();
        goodsDTO.juso = goods.getKoreaArea().getAdmNm();
        goodsDTO.nickId = goods.getMemberId();
        goodsDTO.nick = goods.getNickName();
        goodsDTO.nickPicture = goods.getMemberImages().getSavedFileName();
        goodsDTO.createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(goods.getCreateTime());
        goodsDTO.wishList = goods.getWishList().getId() != null;
        return goodsDTO;
    }

    public GoodsDTO plusGoodsDTO(Goods goods, boolean memberId) {
        GoodsDTO goodsDTO = new GoodsDTO();
        goodsDTO.goodsId = goods.getId();
        goodsDTO.memberId = memberId;
        goodsDTO.picture = goods.getGoodsImages().getSavedFileName();
        goodsDTO.title = goods.getTitle();
        goodsDTO.price = goods.getPrice();
        goodsDTO.sellStatus = goods.getSellStatus();
        goodsDTO.juso = goods.getKoreaArea().getAdmNm();
        goodsDTO.nickId = goods.getMemberId();
        goodsDTO.nick = goods.getNickName();
        goodsDTO.nickPicture = goods.getMemberImages().getSavedFileName();
        goodsDTO.createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(goods.getCreateTime());
        goodsDTO.wishList = goods.getWishList().getId() != null;
        return goodsDTO;
    }

}
