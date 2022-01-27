package pl.mg.blog.post.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mg.blog.commons.ApiErrorResponse;
import pl.mg.blog.post.application.dto.PostDto;
import pl.mg.blog.post.application.dto.PostIdResponse;
import pl.mg.blog.post.application.dto.PostQueryPagedResult;
import pl.mg.blog.post.application.dto.PostQueryResult;
import pl.mg.blog.post.application.service.PostFacade;
import pl.mg.blog.post.core.model.aggregate.Post;
import pl.mg.blog.post.core.model.command.CreatePostCommand;
import pl.mg.blog.post.core.model.command.GetPostCommand;
import pl.mg.blog.post.core.model.command.LikePostCommand;
import pl.mg.blog.post.core.model.command.RemovePostLikeCommand;
import pl.mg.blog.post.core.model.exception.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/post")
@Slf4j
//TODO arguments should be DTOs instead of domain command to provide anti corruption layer
public class PostCommandController {

    protected static final String POST_NOT_FOUND_MESSAGE = "Post not found";
    protected static final String USER_NOT_FOUND_MESSAGE = "User not found";
    protected static final String POST_ALREADY_LIKED_MESSAGE = "Post already liked";
    protected static final String POST_ALREADY_NOT_LIKED_MESSAGE = "Post is not liked already";
    protected static final String USER_BLACKLISTED_MESSAGE = "User is blacklisted";

    private final PostFacade postFacade;


    public PostCommandController(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    //create post
    @PostMapping(value = "")
    public ResponseEntity<PostIdResponse> createPost(@RequestBody @Valid CreatePostCommand command)
            throws pl.mg.blog.post.core.model.exception.UserNotFoundException, ConstraintViolationException {
        log.info("create post {}", command);
        PostIdResponse post = postFacade.createPost(command);
        return ResponseEntity.ok(post);
    }

    //edit post
    @PutMapping(value = "")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostIdResponse> editPost(@RequestBody @Valid pl.mg.blog.post.core.model.command.EditPostCommand command)
            throws PostNotFoundException, UserNotFoundException {
        log.info("editPost {}", command);
        PostIdResponse dto = postFacade.editPost(command);
        return ResponseEntity.ok(dto);
    }

    //getForPostId
    @GetMapping(value = "/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable(name = "postId") @NotEmpty @Valid String postId) throws PostNotFoundException {
        Post post = postFacade.getPost(new GetPostCommand(postId));
        return ResponseEntity.ok(post);
    }

    //check if post exists
    @RequestMapping(value = "/{postId}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> postExists(@PathVariable(name = "postId") @NotEmpty @Valid String postId)
            throws PostNotFoundException {
        Post post = postFacade.getPost(new GetPostCommand(postId));
        if (post != null) {
            return ResponseEntity.ok().build();
        } else {
            throw new PostNotFoundException(POST_NOT_FOUND_MESSAGE);
        }
    }

    //searchPosts
    @GetMapping(value = "/")
    public ResponseEntity<PostQueryPagedResult> search(
            @RequestParam(name = "q", required = false) String q,
            @RequestParam(name = "page", required = false, defaultValue = "0") @Valid @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @Valid @Min(0) int pageSize,
            @RequestParam(name = "sortBy", required = false) @Valid String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "desc") String sortOrder
    ) {

        //TODO implement with CQRS
      /*  SearchPostCommand command = new SearchPostCommand(q,
                new QueryPageableCommand(page, pageSize, sortBy, sortOrder));
        PostQueryPagedResult result = postQueryService.search(command);
        return ResponseEntity.ok(result);*/
        return ResponseEntity.ok(null);
    }

    //get author suggestions
    @GetMapping(value = "/author/suggestions")
    public ResponseEntity<Set<String>> authorSuggestions(
            @RequestParam(name = "q", required = false) String q
    ) {
        //TODO implement Query service in CQRS
        //Set<String> result = postQueryService.getAuthorSuggestions(q);
        return ResponseEntity.ok(null);
    }



    //getPostsForUserId
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<PostDto>> getUserPosts(
            @PathVariable(name = "userId") @Valid @NotEmpty String userId) {
        //TODO implement Query service in CQRS
        return ResponseEntity.ok(null);
    }

    //getForCommentId
    @GetMapping(value = "/comment/{commentId}")
    public ResponseEntity<PostQueryResult> getPostByCommentId(
            @PathVariable(name = "commentId") @Valid @NotEmpty String commentId)
            throws PostNotFoundException {
        //TODO implement Query service in CQRS
       /* Optional<PostQueryResult> post = postQueryService.findByCommentId(commentId);
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            throw new PostNotFoundException(POST_NOT_FOUND_MESSAGE);
        }*/
        throw new PostNotFoundException(POST_NOT_FOUND_MESSAGE);
    }

    @PutMapping(value = "/{postId}/like/{username}")
    public ResponseEntity<Void> likePost(@PathVariable(name = "postId") @Valid @NotEmpty String postId,
                                         @PathVariable(name = "username") @Valid @NotEmpty String username)
            throws UserNotFoundException, PostNotFoundException, PostAlreadyLikedException, UserBlacklistedException {
        this.postFacade.likePost(new LikePostCommand(postId, username));
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(value = "/{postId}/like/{username}")
    public ResponseEntity<Void> removeLikeFromPost(@PathVariable(name = "postId") @Valid @NotEmpty String postId,
                                                   @PathVariable(name = "username") @Valid @NotEmpty String username)
            throws UserNotFoundException, PostNotFoundException, UserBlacklistedException, PostAlreadyNotLikedException {
        this.postFacade.removePostLike(new RemovePostLikeCommand(postId, username));
        return ResponseEntity.ok(null);
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

    @ExceptionHandler(value = {PostAlreadyLikedException.class})
    public ResponseEntity<ApiErrorResponse> handlePostAlreadyLiked(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(POST_ALREADY_LIKED_MESSAGE, details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(value = {PostAlreadyNotLikedException.class})
    public ResponseEntity<ApiErrorResponse> handlePostAlreadyNotLiked(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(POST_ALREADY_NOT_LIKED_MESSAGE, details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(value = {UserBlacklistedException.class})
    public ResponseEntity<ApiErrorResponse> handleUserBlacklisted(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(USER_BLACKLISTED_MESSAGE, details);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiErrorResponse);
    }

}
