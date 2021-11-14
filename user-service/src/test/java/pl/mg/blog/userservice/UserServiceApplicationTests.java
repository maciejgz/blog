package pl.mg.blog.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
class UserServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
