package marcat.members.dao;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import marcat.members.dto.KakaoResponseDTO;
import marcat.members.vo.Member;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@ToString
public class KakaoMapDAO {

    private final Map<String, KakaoResponseDTO> map = ExpiringMap.builder().expiration(1200, TimeUnit.SECONDS).build();

    public void addElement(String uuid, KakaoResponseDTO kakaoResponseDTO) {
        Iterator<String> keys = map.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            if (kakaoResponseDTO.getId().equals(map.get(key).getId())) {
                map.remove(key);
            }
        }
        map.putIfAbsent(uuid, kakaoResponseDTO);
    }

    public KakaoResponseDTO getElement(String uuid) {
        KakaoResponseDTO resultKakao = map.get(uuid);
        return resultKakao;
    }
}
