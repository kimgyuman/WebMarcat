package marcat.managerpage.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GoodsListDTO {
    private Long id;
    private String category;
    private String sidoNm;
    private String nickName;
    private String title;
    private String contents;
    private String sellStatus;
    private int viewCount;
    private LocalDate createTime;
    private int price;
    private int wishCount;
    private String status;
}
