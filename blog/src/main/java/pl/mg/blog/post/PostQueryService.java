package pl.mg.blog.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostQueryService {

    private final PostRepository repository;

    public PostQueryService(PostRepository repository) {
        this.repository = repository;
    }

    public Optional<PostQueryResult> findByID(@Valid @NotEmpty String id) {
        Optional<Post> post = this.repository.findById(id);
        return post.map(PostQueryResult::ofPost);
    }

    public List<PostQueryResult> findByUsername(@Valid @NotEmpty String userId) {
        List<Post> allByAuthor = repository.findAllByAuthor(userId);
        return allByAuthor.stream().map(PostQueryResult::ofPost).collect(Collectors.toList());
    }

    public Optional<PostQueryResult> findByCommentId(@Valid @NotEmpty String commentId) {
        Optional<Post> byComment = repository.findFirstByCommentIdsContains(commentId);
        return byComment.map(PostQueryResult::ofPost);
    }

    public Optional<PostQueryResult> getRandomPost() {
        return repository.getRandomPost().map(PostQueryResult::ofPost);
    }

    public PostQueryPagedResult search(SearchPostCommand command) {
        //TODO implement
        return null;
    }
}
