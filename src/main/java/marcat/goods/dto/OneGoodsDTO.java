package marcat.goods.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import marcat.goods.vo.Categories;
import marcat.goods.vo.Goods;
import marcat.goods.vo.KoreaArea;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@ToString
public class OneGoodsDTO {
    private Long id;
    private Long memberId;
    private String admCd8;
    private String categoryId;
    private String title;
    private String contents;
    private String sellStatus;
    private int viewCount;
    private String createTime;
    private int price;
    private String negoStatus;
    private int wishCount;
    private String status;
    private String nickName;

    private List<String> goodsImages;
    private String categoriesName;
    private String koreaArea;
    private String memberImages;

    private boolean wishList;
    private boolean reportOne;
    private boolean requestBuy;
    private String requestResult;

    protected OneGoodsDTO() {
    }

    public OneGoodsDTO(Long id, Goods searchedOne, List<String> goodsImages, boolean wishList, boolean reportOne, boolean requestOne, String requestResult) {
        this.id = id;
        this.memberId = searchedOne.getMemberId();
        this.admCd8 = searchedOne.getAdmCd8();
        this.categoryId = searchedOne.getCategoryId();
        this.title = searchedOne.getTitle();
        this.contents = searchedOne.getContents();
        this.sellStatus = searchedOne.getSellStatus();
        this.viewCount = searchedOne.getViewCount();
        this.createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(searchedOne.getCreateTime());
        this.price = searchedOne.getPrice();
        this.negoStatus = searchedOne.getNegoStatus();
        this.wishCount = searchedOne.getWishCount();
        this.status = searchedOne.getStatus();
        this.nickName = searchedOne.getNickName();
        this.goodsImages = goodsImages;
        this.categoriesName = searchedOne.getCategories().getName();
        this.koreaArea = searchedOne.getKoreaArea().getAdmNm();
        this.memberImages = searchedOne.getMemberImages().getSavedFileName();
        this.wishList = wishList;
        this.reportOne = reportOne;
        this.requestBuy = requestOne;
        this.requestResult = requestResult;
    }
}
