import marcat.members.dto.LoginDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class BeanValidationTest {
    
    @Test
    void beanValidation() {
        //검증기 생성
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        LoginDTO loginDTO = new LoginDTO("test", "test");

        Set<ConstraintViolation<LoginDTO>> violations = validator.validate(loginDTO);
        for (ConstraintViolation<LoginDTO> violation : violations) {
            System.out.println("violation = " + violation);
            System.out.println("violation.getMessage() = " + violation.getMessage());
        }
    }

//    @Test
//    void memberBeanValidation() {
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//        Member member = Member.builder()
//                .id(" ")
//                .password("test")
//                .year(1899)
//                .build();
//        Set<ConstraintViolation<Member>> violations = validator.validate(member);
//        for (ConstraintViolation<Member> violation : violations) {
//            System.out.println("violation = " + violation);
//            System.out.println("violation.message = " + violation.getMessage());
//        }
//    }

}
