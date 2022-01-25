package pl.mg.blog.legacy.post.user.it;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mg.blog.legacy.post.user.client.UserClient;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"pl.mg.blog:user-service:+:stubs:8585"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class UserServiceFeignIT {

    @Autowired
    private UserClient userClient;

    @Test
    void getUser1Test() {
        userClient.checkIfUserExists("user1");
    }
}
