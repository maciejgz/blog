package pl.mg.blog.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mg.blog.comment.api.CommentSort;
import pl.mg.blog.commons.ApiErrorResponse;
import pl.mg.blog.commons.QueryPageableCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping(value = "/post")
@Slf4j
public class PostController {

    protected static final String POST_NOT_FOUND_MESSAGE = "Post not found";
    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;

    public PostController(PostCommandService postCommandService, PostQueryService postQueryService) {
        this.postCommandService = postCommandService;
        this.postQueryService = postQueryService;
    }

    //create post
    @PostMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity createPost(@RequestBody @Valid CreatePostResponse dto, Authentication authentication) {
        log.debug("create post");
        //TODO add object factory in the aggregate
        postCommandService.createPost(
                new CreatePostCommand(authentication.getName(), dto.getTitle(), dto.getContent()));
        return ResponseEntity.ok().build();
    }

    //edit post
    @PutMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity editPost(@RequestBody @Valid EditPostResponse dto, Authentication authentication)
            throws PostNotFoundException {
        log.debug("editPost");
        postCommandService.editPost(
                new EditPostCommand(dto.getId(), authentication.getName(), dto.getTitle(), dto.getContent()));
        return ResponseEntity.ok().build();
    }

    //getForPostId
    @GetMapping(value = "/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostQueryResult> getPost(@PathVariable(name = "postId") @NotEmpty @Valid String postId)
            throws PostNotFoundException {
        Optional<PostQueryResult> post = postQueryService.findByID(postId);
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            throw new PostNotFoundException(POST_NOT_FOUND_MESSAGE);
        }
    }

    //searchPosts
    @GetMapping(value = "/")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostQueryPagedResult> search(
            @RequestParam(name = "q", required = false) String q,
            @RequestParam(name = "page", required = false, defaultValue = "0") @Valid @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @Valid @Min(0) int pageSize,
            @RequestParam(name = "sortBy", required = false) @Valid @CommentSort String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "desc") String sortOrder
    ) {
        SearchPostCommand command = new SearchPostCommand(q,
                new QueryPageableCommand(page, pageSize, sortBy, Sort.Direction.fromString(sortOrder)));
        PostQueryPagedResult result = postQueryService.search(command);
        return ResponseEntity.ok(result);
    }

    //get author suggestions
    @GetMapping(value = "/author/suggestions")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Set<String>> authorSuggestions(
            @RequestParam(name = "q", required = false) String q
    ) {
        Set<String> result = postQueryService.getAuthorSuggestions(q);
        return ResponseEntity.ok(result);
    }

    //getRandomPost
    @GetMapping(value = "/random")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostQueryResult> getRandomPost() throws PostNotFoundException {
        Optional<PostQueryResult> post = postQueryService.getRandomPost();
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            throw new PostNotFoundException("Any post not found");
        }
    }

    //getPostsForUserId
    @GetMapping(value = "/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<PostQueryResult>> getUserPosts(
            @PathVariable(name = "userId") @Valid @NotEmpty String userId) {
        return ResponseEntity.ok(postQueryService.findByUsername(userId));
    }

    //getForCommentId
    @GetMapping(value = "/comment/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostQueryResult> getPostByCommentId(
            @PathVariable(name = "commentId") @Valid @NotEmpty String commentId)
            throws PostNotFoundException {
        Optional<PostQueryResult> post = postQueryService.findByCommentId(commentId);
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            throw new PostNotFoundException(POST_NOT_FOUND_MESSAGE);
        }
    }

    @ExceptionHandler(value = {PostNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> handlePostNotFound(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(POST_NOT_FOUND_MESSAGE, details);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }
}
