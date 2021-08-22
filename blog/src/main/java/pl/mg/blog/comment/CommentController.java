package pl.mg.blog.comment;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/comment")
@Slf4j
public class CommentController {

    //post comment
    @PostMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<QueryCommentDto> addComment(@RequestBody @Valid AddCommentCommand command) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //like comment
    @PostMapping(value = "/like")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<QueryCommentDto>> likeComment(@RequestBody LikeCommentCommand command) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //get by id
    @GetMapping(value = "/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<QueryCommentDto> getComment(@PathVariable long commentId) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //get all user comments
    @GetMapping(value = "/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<QueryCommentDto>> getUserComments(@PathVariable long userId) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //get all for post
    @GetMapping(value = "/post/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<QueryCommentDto>> getPostComments(@PathVariable long postId) {
        //TODO
        return ResponseEntity.ok(null);
    }
}
