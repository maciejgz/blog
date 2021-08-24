package pl.mg.blog.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Optional;

@Service
@Slf4j
public class PostCommandService {

    private final PostRepository postRepository;

    public PostCommandService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(@Valid CreatePostCommand command) {
        log.debug("createPost() called with: command = [" + command + "]");
        Post post = new Post(null, command.getUsername(), command.getTitle(), command.getContent(),
                Instant.now(), null, 0L, null);
        postRepository.save(post);
    }

    @PreAuthorize("@postAccessService.hasPermission(#command.username, #command.id)")
    public void editPost(@Valid EditPostCommand command) throws PostNotFoundException {
        Optional<Post> post = postRepository.findById(command.getId());
        if (post.isPresent()) {
            Post edit = post.get();
            if (edit.getAuthor().equals(command.getUsername())) {
                edit.setContent(command.getContent());
                edit.setTitle(command.getTitle());
                edit.setUpdatedAt(Instant.now());
                postRepository.save(edit);
            }
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

}
