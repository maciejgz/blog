package pl.mg.blog.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
class GatewayServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
