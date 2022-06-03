import lombok.extern.slf4j.Slf4j;
import marcat.members.dao.KakaoMapDAO;
import marcat.members.dto.KakaoResponseDTO;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class ExpiringMapTest {

    @Autowired
    private KakaoMapDAO kakaoMapDAO;

    private static final Map<String, Double> map = ExpiringMap.builder().expiration(5, TimeUnit.SECONDS).build();

    private static void addElement() {

        double randomValue = Math.random();
        map.putIfAbsent("Crunchify " + randomValue, randomValue);


    }

    @Test
    void ExpiringMapTest() {
//        ExpiringMap<String , String > map = ExpiringMap.builder().build();

//        System.out.println("map.size() = " + map.size());
//        map.put("key-1", "value-1", ExpirationPolicy.CREATED, 30, TimeUnit.SECONDS);
//        map.put("key-2", "value-2", ExpirationPolicy.ACCESSED, 2, TimeUnit.MINUTES);

//        map.put("num", "test", ExpirationPolicy.ACCESSED, 5L, TimeUnit.SECONDS);
//        System.out.println("map.size() = " + map.size());

        // NOTE: We are adding Unique Element to Map Every time.
        while (true) {
            try {
                // Just wait for a second everytime
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Add Element to Map crunchifyMap
            addElement();

            // Print Element to Map crunchifyMap
            System.out.println("map = " + map.size() + map.values() + map.keySet());
        }

    }

    @Test
    void nullTest() {
        KakaoResponseDTO kakaoResponseDTO = new KakaoResponseDTO();
        kakaoResponseDTO.setId("1");
        kakaoMapDAO.addElement("123", kakaoResponseDTO);
        KakaoResponseDTO element = kakaoMapDAO.getElement("12");
        log.info("element={}", element);
    }
    
}
