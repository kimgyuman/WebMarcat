package marcat.goods.vo;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Commercial {

    private Long id;
    private int categoryId;
    private String title;
    private LocalDateTime createTime;
    private LocalDateTime expiryTime;
    private ViewStatus viewStatus;
    private String originFileName;
    private String savedFileName;
    private String url;

    public Commercial() {}

    public Commercial(int categoryId, String title, LocalDateTime expiryTime, String originFileName, String savedFileName, String url) {
        this.categoryId = categoryId;
        this.title = title;
        this.expiryTime = expiryTime;
        this.originFileName = originFileName;
        this.savedFileName = savedFileName;
        this.url = url;
    }

    public Commercial(Long id, int categoryId, String title, LocalDateTime expiryTime, String originFileName, String savedFileName, String url) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.expiryTime = expiryTime;
        this.originFileName = originFileName;
        this.savedFileName = savedFileName;
        this.url = url;
    }
}
