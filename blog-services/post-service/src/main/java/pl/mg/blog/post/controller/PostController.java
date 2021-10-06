package pl.mg.blog.post.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mg.blog.commons.ApiErrorResponse;
import pl.mg.blog.post.dto.*;
import pl.mg.blog.post.service.PostCommandServiceImpl;
import pl.mg.blog.post.service.PostQueryService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/post")
@Slf4j
public class PostController {

    protected static final String POST_NOT_FOUND_MESSAGE = "Post not found";
    protected static final String USER_NOT_FOUND_MESSAGE = "User not found";
    private final PostCommandServiceImpl postCommandServiceImpl;
    private final PostQueryService postQueryService;

    public PostController(PostCommandServiceImpl postCommandServiceImpl, PostQueryService postQueryService) {
        this.postCommandServiceImpl = postCommandServiceImpl;
        this.postQueryService = postQueryService;
    }

    //create post
    @PostMapping(value = "")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostQueryResult> createPost(@RequestBody @Valid CreatePostResponse dto) throws UserNotFoundException {
        log.debug("create post");
        //TODO add object factory in the aggregate
        PostQueryResult post = postCommandServiceImpl.createPost(
                new CreatePostCommand(dto.getAuthor(), dto.getTitle(), dto.getContent()));
        return ResponseEntity.ok(post);
    }

    //getAllPosts
    @GetMapping(value = "")
    public ResponseEntity<List<PostQueryResult>> getPosts() {
        return ResponseEntity.ok(postQueryService.getAll());
    }


    //edit post
    @PutMapping(value = "")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> editPost(@RequestBody @Valid EditPostResponse dto)
            throws PostNotFoundException, UserNotFoundException {
        log.debug("editPost");
        postCommandServiceImpl.editPost(
                new EditPostCommand(dto.getId(), dto.getAuthor(), dto.getTitle(), dto.getContent()));
        return ResponseEntity.ok().build();
    }

    //getForPostId
    @GetMapping(value = "/{postId}")
//    @PreAuthorize("isAuthenticated()")
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
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostQueryPagedResult> search(
            @RequestParam(name = "q", required = false) String q,
            @RequestParam(name = "page", required = false, defaultValue = "0") @Valid @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @Valid @Min(0) int pageSize,
            @RequestParam(name = "sortBy", required = false) @Valid String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "desc") String sortOrder
    ) {
        SearchPostCommand command = new SearchPostCommand(q,
                new QueryPageableCommand(page, pageSize, sortBy, sortOrder));
        PostQueryPagedResult result = postQueryService.search(command);
        return ResponseEntity.ok(result);
    }

    //get author suggestions
    @GetMapping(value = "/author/suggestions")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Set<String>> authorSuggestions(
            @RequestParam(name = "q", required = false) String q
    ) {
        Set<String> result = postQueryService.getAuthorSuggestions(q);
        return ResponseEntity.ok(result);
    }

    //getRandomPost
    @GetMapping(value = "/random")
//    @PreAuthorize("isAuthenticated()")
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
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<PostQueryResult>> getUserPosts(
            @PathVariable(name = "userId") @Valid @NotEmpty String userId) {
        return ResponseEntity.ok(postQueryService.findByUsername(userId));
    }

    //getForCommentId
    @GetMapping(value = "/comment/{commentId}")
//    @PreAuthorize("isAuthenticated()")
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

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> handleUserNotFound(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(USER_NOT_FOUND_MESSAGE, details);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }
}
