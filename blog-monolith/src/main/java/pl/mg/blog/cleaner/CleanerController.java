package pl.mg.blog.cleaner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mg.blog.comment.CommentRepository;
import pl.mg.blog.comment.repository.search.CommentSearchRepository;
import pl.mg.blog.post.PostRepository;
import pl.mg.blog.post.repository.search.PostSearchRepository;

@RestController
@RequestMapping(path = "/util/clean")
@Slf4j
public class CleanerController {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final PostSearchRepository postSearchRepository;
    private final CommentSearchRepository commentSearchRepository;

    public CleanerController(CommentRepository commentRepository, PostRepository postRepository,
            PostSearchRepository postSearchRepository,
            CommentSearchRepository commentSearchRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.postSearchRepository = postSearchRepository;
        this.commentSearchRepository = commentSearchRepository;
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Boolean> cleanBlogData() {
        this.commentRepository.deleteAll();
        this.postRepository.deleteAll();
        this.postSearchRepository.deleteAll();
        this.commentSearchRepository.deleteAll();
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
