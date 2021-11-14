package pl.mg.blog.post;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
class PostServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
