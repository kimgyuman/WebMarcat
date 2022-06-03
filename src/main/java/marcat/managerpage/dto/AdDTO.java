package marcat.managerpage.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AdDTO {
    private Long id;
    private String categoriesName;
    private String title;
    private LocalDate createTime;
    private LocalDate expiryTime;
    private String viewStatus;
}
