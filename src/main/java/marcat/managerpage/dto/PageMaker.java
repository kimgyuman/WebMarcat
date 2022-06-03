package marcat.managerpage.dto;

import lombok.Getter;

@Getter
public class PageMaker {
    private int endPage;
    private final int startPage;
    private final int realEnd;
    private final int total;
    private final int count;

    boolean prev, next;

    PageParam pageParam;

    public PageMaker(PageParam pageParam, int total) {

        this.pageParam = pageParam;
        this.total = total;

        int current = pageParam.getPage();
        this.count = pageParam.getReal();

        this.endPage = (int)( Math.ceil(current*0.1))*10;

        this.startPage = endPage-9;

        this.realEnd = (int) (Math.ceil((total * 1.0) / count));

        if(realEnd < endPage) {
            this.endPage = realEnd;
        }

        this.prev = current > 1;
        this.next = current < realEnd;
    }
}
