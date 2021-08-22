package pl.mg.blog.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/post")
@Slf4j
public class PostController {

    private final PostCommandService postCommandService;
    private final QueryPostService queryPostService;

    public PostController(PostCommandService postCommandService, QueryPostService queryPostService) {
        this.postCommandService = postCommandService;
        this.queryPostService = queryPostService;
    }

    //create post
    @PostMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity createPost(@RequestBody @Valid CreatePostDto dto, Authentication authentication) {
        log.debug("create post");
        //TODO add factory
        postCommandService.createPost(new CreatePostCommand(authentication.getName(), dto.getTitle(), dto.getContent()));
        return ResponseEntity.ok().build();
    }

    //edit post
    @PutMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<QueryPostDto> editPost(@RequestBody @Valid EditPostDto command) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //getForPostId
    @GetMapping(value = "/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<QueryPostDto> getPost(@PathVariable(name = "postId") UUID postId) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //getPostsForUserId
    @GetMapping(value = "/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<QueryPostDto>> getUserPosts(@PathVariable(name = "userId") UUID userId) {
        //TODO
        return ResponseEntity.ok(null);
    }

    //getForCommentId
    @GetMapping(value = "/comment/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<QueryPostDto> getPostByCommentId(@PathVariable(name = "commentId") UUID commentId) {
        //TODO
        return ResponseEntity.ok(null);
    }


}
