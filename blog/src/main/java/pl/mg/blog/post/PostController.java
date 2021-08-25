package pl.mg.blog.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.mg.blog.commons.ApiErrorResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/post")
@Slf4j
public class PostController {

    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;

    public PostController(PostCommandService postCommandService, PostQueryService postQueryService) {
        this.postCommandService = postCommandService;
        this.postQueryService = postQueryService;
    }

    //create post
    @PostMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity createPost(@RequestBody @Valid CreatePostDto dto, Authentication authentication) {
        log.debug("create post");
        //TODO add object factory in the aggregate
        postCommandService.createPost(new CreatePostCommand(authentication.getName(), dto.getTitle(), dto.getContent()));
        return ResponseEntity.ok().build();
    }

    //edit post
    @PutMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity editPost(@RequestBody @Valid EditPostDto dto, Authentication authentication) throws PostNotFoundException {
        log.debug("editPost");
        postCommandService.editPost(new EditPostCommand(dto.getId(), authentication.getName(), dto.getTitle(), dto.getContent()));
        return ResponseEntity.ok().build();
    }

    //getForPostId
    @GetMapping(value = "/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<QueryPostDto> getPost(@PathVariable(name = "postId") @NotEmpty @Valid String postId) throws PostNotFoundException {
        Optional<QueryPostDto> post = postQueryService.findByID(postId);
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    //getPostsForUserId
    @GetMapping(value = "/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<QueryPostDto>> getUserPosts(@PathVariable(name = "userId") @Valid @NotEmpty String userId) {
        return ResponseEntity.ok(postQueryService.findByUsername(userId));
    }

    //getForCommentId
    @GetMapping(value = "/comment/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<QueryPostDto> getPostByCommentId(@PathVariable(name = "commentId") @Valid @NotEmpty String commentId)
            throws PostNotFoundException {
        Optional<QueryPostDto> post = postQueryService.findByCommentId(commentId);
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    @ExceptionHandler(value = {PostNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> handlePostNotFound(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse("Post not found", details);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }
}
