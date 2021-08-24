package pl.mg.blog.post.security;

import org.springframework.stereotype.Component;
import pl.mg.blog.post.PostRepository;

@Component("postAccessService")
public class PostAccessService {

    private final PostRepository postRepository;

    public PostAccessService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public boolean hasPermission(String username, String postId) {
        return postRepository.findByAuthorAndId(username, postId).isPresent();
    }
}
