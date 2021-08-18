package pl.mg.blog.comment;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

    @GetMapping(value = "/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommentDto>> getPostComments(@PathVariable long postId) {
        List<CommentDto> comments = new ArrayList<>();
        comments.add(new CommentDto(1, "c1", "a1", 1, Instant.now(), 34));
        comments.add(new CommentDto(2, "c2", "a2", 1, Instant.now(), 0));
        return ResponseEntity.ok(comments);
    }
}
