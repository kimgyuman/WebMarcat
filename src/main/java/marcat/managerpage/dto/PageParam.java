package marcat.managerpage.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public class PageParam {

    private int page = 1;
    private int amount = 10;
    private int start = 0;
    private int real = 10;
    private String search = "";
    private String keyword = "";


    public void setPage(int page) {
        this.page = page;
        this.start = (page-1)*amount;
        this.amount = ((page-1)*amount)+amount;
    }

}