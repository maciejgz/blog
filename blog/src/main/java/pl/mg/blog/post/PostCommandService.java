package pl.mg.blog.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
public class PostCommandService {

    private final PostRepository postRepository;

    public PostCommandService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(CreatePostCommand command) {
        log.debug("createPost() called with: command = [" + command + "]");
        Post post = new Post(UUID.randomUUID(), command.getUsername(), command.getTitle(), command.getContent(),
                Instant.now(), 0L);
        postRepository.save(post);
    }

}
