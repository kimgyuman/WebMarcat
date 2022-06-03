package marcat.members.dao;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@ToString
public class SmsAuthCodeMapDAO {

    private final Map<String, Double> map = ExpiringMap.builder().expiration(60, TimeUnit.SECONDS).build();

    public void addElement(String phoneNum, Double authCode) {
        if (map.get(phoneNum) != null) {
            map.remove(phoneNum);
            map.putIfAbsent(phoneNum, authCode);
        } else {
            map.putIfAbsent(phoneNum, authCode);
        }
    }

    public boolean checkElement(String phoneNum, Double authCode) {
        boolean result = false;
        Double num = map.get(phoneNum);
        if (num == null) {
            result = false;
        } else result = Double.compare(num, authCode) == 0;
        return result;
    }
}
