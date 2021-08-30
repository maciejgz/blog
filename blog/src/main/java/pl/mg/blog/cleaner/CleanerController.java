package pl.mg.blog.cleaner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mg.blog.comment.CommentRepository;
import pl.mg.blog.post.PostRepository;

@RestController
@RequestMapping(path = "/util/clean")
@Slf4j
public class CleanerController {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CleanerController(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Boolean> cleanBlogData() {
        this.commentRepository.deleteAll();
        this.postRepository.deleteAll();
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
