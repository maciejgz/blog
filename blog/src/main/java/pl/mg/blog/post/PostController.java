package pl.mg.blog.post;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/post")
public class PostController {

    //create post
    @PostMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePost command) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //edit post
    @PutMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostDto> editPost(@RequestBody EditPost command) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //getForPostId
    @GetMapping(value = "/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostDto> getPost(@PathVariable(name = "postId") Long postId) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //getPostsForUserId
    @GetMapping(value = "/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<PostDto>> getUserPosts(@PathVariable(name = "userId") Long userId) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //getForCommentId
    @GetMapping(value = "/comment/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostDto> getPostByCommentId(@PathVariable(name = "commentId") Long commentId) {
        //TODO
        return ResponseEntity.ok(null);
    }


}
