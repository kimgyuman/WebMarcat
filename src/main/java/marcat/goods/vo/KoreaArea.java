package marcat.goods.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KoreaArea {

    private String admCd8;
    private String admNm;
    private String admCd;
    private String admCd2;
    private String sgg;
    private String sido;
    private String sidoNm;
    private String temp;
    private String sggNm;

    //    ADM_CD8 varchar2 (11110660)
//    ADM_NM varchar2 (서울특별시 노원구 상계2동)
//    ADM_CD varchar2 (1111066)
//    ADM_CD2 varchar2 (1135064000)
//    SGG varchar2 (11350)
//    SIDO varchar2 (11)
//    SIDONM varchar2 (서울특별시)
//    TEMP varchar2 (노원구 상계2동	)
//    SGGNM varchar2 (노원구)


    public KoreaArea(String admCd8, String admNm) {
        this.admCd8 = admCd8;
        this.admNm = admNm;
    }

    protected KoreaArea() {
    }


}
