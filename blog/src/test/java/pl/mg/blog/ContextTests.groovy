package pl.mg.blog

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.context.SpringBootTest
import pl.mg.blog.post.PostController
import spock.lang.Specification

@SpringBootTest
@DataMongoTest
class ContextTests extends Specification {


    @Autowired
    private PostController postController;

    def "when context is created then all expected beans are created"() {
        when:

        then:

        expect: "the post controller is created"
        postController
    }
}
