package marcat;

import org.junit.jupiter.api.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

public class ViewCountServiceTest {

    @Test
    void stringContainsTest() {
        Long id = 54L;
        String test = "[" + id + "]";
        boolean contains = test.contains("[" + id + "]");
        System.out.println("contains = " + contains);
    }

    @Test
    void stringCookieTest() {
        ArrayList<Cookie> oldCookie = new ArrayList<>();
        Cookie cookie = new Cookie("V-TOKEN" + 1, "1");
        oldCookie.add(cookie);
        if (oldCookie != null) {
            for (Cookie co : oldCookie) {
                if (cookie.getName().contains("V-TOKEN")) {
                    System.out.println("co = " + co);
                }
            }
        }
        Cookie c = oldCookie.get(0);
        System.out.println("c = " + c.getValue());
    }

    @Test
    void getHour() {
        Date d = new Date();
        System.out.println(d.getHours()%7);
    }
}
