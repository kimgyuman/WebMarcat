package marcat.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportContents {
    SPAM("스팸홍보/도배글입니다"), ADULT("음란물입니다"),
    ABUSE("욕설/혐오/차별적 표현이 포함되어 있습니다"), PRIVACY("개인정보 노출 게시물입니다"),
    TEENAGER("청소년에게 유해한 내용입니다"), COPYRIGHT("저작권침해 게시물입니다");

    private String value;
}
