import marcat.aws.AwsS3Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class AwsTest {

    @Autowired
    public AwsS3Service managerPageService;
    @Autowired
    public ServletContext servletContext;

    @Test
    void awsTest() throws IOException {
        String realPath = servletContext.getRealPath("resources/img/logo.png");
        System.out.println("realPath = " + realPath);
        File file = new File(realPath);
//        File file = new File("/Users/lee/Downloads/Mediumish_Free_Website_Template/mediumish-html/assets/img/demopic/10.jpg");
        String key = "img";
        String upload = managerPageService.upload(file, key);
        System.out.println("upload = " + upload);
    }
}
