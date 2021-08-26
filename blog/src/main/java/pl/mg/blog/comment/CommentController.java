package pl.mg.blog.comment;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //post comment
    @PostMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentQueryResult> addComment(@RequestBody @Valid AddCommentCommand command, Authentication authentication) {
        command.setUsername(authentication.getName());
        CommentQueryResult commentQueryResult = commentService.addComment(command);
        return ResponseEntity.ok(commentQueryResult);
    }

    //like comment
    @PostMapping(value = "/like")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommentQueryResult>> likeComment(@RequestBody LikeCommentCommand command) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //dislike comment
    @PostMapping(value = "/dislike")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommentQueryResult>> dislikeComment(@RequestBody DislikeCommentCommand command) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //get by id
    @GetMapping(value = "/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentQueryResult> getComment(@PathVariable long commentId) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //get all user comments
    @GetMapping(value = "/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommentQueryResult>> getUserComments(@PathVariable long userId) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //get all comments for post
    @GetMapping(value = "/post/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommentQueryResult>> getPostComments(@PathVariable long postId) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //get all comments for post
    @GetMapping(value = "/like/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommentQueryResult>> getUserLikedComments(@PathVariable long userId) {
        //TODO
        return ResponseEntity.ok(null);
    }
}
