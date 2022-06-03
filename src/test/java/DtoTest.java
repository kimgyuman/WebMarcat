import lombok.extern.slf4j.Slf4j;
import marcat.goods.service.GoodsService;
import marcat.goods.vo.Categories;
import marcat.members.dto.JusoDTO;
import marcat.members.dto.SignUpDTO;
import marcat.members.service.MemberService;
import marcat.members.vo.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Slf4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class DtoTest {

//    @BeforeAll
//    public static void beforeClass() {
//        Locale.setDefault(Locale.KOREA); // locale 설정에 따라 에러 메시지가 달라진다.
//    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MemberService memberService;

    @Validated
    private static SignUpDTO createSignUp(String juso, String id, String ps, String name, String nick, String phone) {
        SignUpDTO signUpDTO = new SignUpDTO(juso, id, ps, name, nick, phone);
        return signUpDTO;
    }

    @Test
    void SignUpDTOTest() {
        // Given
        Locale.setDefault(Locale.KOREAN);
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        SignUpDTO signUp = createSignUp("111", "aaaa", "1234qwer!", "테스트", "테스트", "01011112222");
        Set<ConstraintViolation<SignUpDTO>> constraintViolations = validator.validate(signUp);
        BindingResult bindingResult;
        log.info("constraintViolations={}", constraintViolations);
    }

    @Test
    void passwordEncodeTest() {
        String id = "1111111";
        String encode = passwordEncoder.encode(id);
        System.out.println("encode = " + encode);
    }

    @Test
    void voSameTest() {
        User user1 = new User();
        user1.setId("1");
        user1.setPw("1");

        User user2 = new User();
        user2.setId("1");
        user2.setPw("1");

        if (user1.equals(user2)) {
            System.out.println("같음");
        } else {
            System.out.println("다름");
        }
    }

    @Test
    void random() {
        int authNo = (int)(Math.random() * (999999 - 100000 + 1)) + 100000;
        System.out.println(authNo);
    }

    public class User {
        private String id;
        private String pw;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPw() {
            return pw;
        }

        public void setPw(String pw) {
            this.pw = pw;
        }
    }


    @Test
    void CateTest() {
        List<Categories> categoriesList = goodsService.category_list();
        System.out.println("categoriesList = " + categoriesList);
    }

    @Test
    void findById() {
        Member byId = memberService.findById(1L);
        System.out.println("byId = " + byId);
    }

    @Test
    void findByStringForJuso() {
        List<JusoDTO> 삼전동 = goodsService.pullArea("삼전동");
        System.out.println("삼전동 = " + 삼전동);
    }
}
