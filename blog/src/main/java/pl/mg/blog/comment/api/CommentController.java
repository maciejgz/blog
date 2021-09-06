package pl.mg.blog.comment.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mg.blog.comment.*;
import pl.mg.blog.comment.exception.CommentAlreadyDislikedException;
import pl.mg.blog.comment.exception.CommentAlreadyLikedException;
import pl.mg.blog.comment.exception.CommentNotExistException;
import pl.mg.blog.commons.QueryPageableCommand;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/comment")
@Slf4j
@Validated
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //post comment
    @PostMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentQueryResult> addComment(@Valid @RequestBody AddCommentCommand command,
                                                         Authentication authentication) {
        command.setUsername(authentication.getName());
        CommentQueryResult commentQueryResult = commentService.addComment(command);
        return ResponseEntity.ok(commentQueryResult);
    }

    //like comment
    @PostMapping(value = "/like")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<LikeCommentResponse> likeComment(@Valid @RequestBody LikeCommentCommand command,
                                                           Authentication authentication) throws CommentNotExistException, CommentAlreadyLikedException {
        command.setUsername(authentication.getName());
        LikeCommentResponse likeCommentResponse = commentService.likeComment(command);
        return ResponseEntity.ok(likeCommentResponse);
    }

    //dislike comment
    @PostMapping(value = "/dislike")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DislikeCommentResponse> dislikeComment(@Valid @RequestBody DislikeCommentCommand command,
                                                                 Authentication authentication)
            throws CommentNotExistException, CommentAlreadyDislikedException {
        command.setUsername(authentication.getName());
        DislikeCommentResponse dislikeCommentResponse = commentService.dislikeComment(command);
        return ResponseEntity.ok(dislikeCommentResponse);
    }

    //get by comment id
    @GetMapping(value = "/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentQueryResult> getComment(@PathVariable @Valid @NotNull String commentId)
            throws CommentNotExistException {
        CommentQueryResult comment = commentService.getComment(commentId);
        return ResponseEntity.ok(comment);
    }

    //get all user comments
    @GetMapping(value = "/user/{username}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentQueryPageResult> getUserComments(@PathVariable @Valid @NotEmpty String username,
                                                                  @RequestParam(name = "page", required = false, defaultValue = "0") @Valid @Min(0) Integer page,
                                                                  @RequestParam(name = "pageSize", required = false, defaultValue = "20") @Valid @Min(0) int pageSize,
                                                                  @RequestParam(name = "sortBy", required = false, defaultValue = "created") @Valid @CommentSort String sortBy,
                                                                  @RequestParam(name = "sortOrder", required = false, defaultValue = "desc") String sortOrder
    ) {
        GetCommentsByUserIdCommand command = new GetCommentsByUserIdCommand();
        command.setUsername(username);
        QueryPageableCommand queryPageableCommand = new QueryPageableCommand(page, pageSize, sortBy, Sort.Direction.fromString(sortOrder));
        command.setPageableCommand(queryPageableCommand);
        CommentQueryPageResult userComments = commentService.getUserComments(command);
        return ResponseEntity.ok(userComments);
    }

    //get all comments for post
    @GetMapping(value = "/post/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommentQueryResult>> getPostComments(@PathVariable @Valid @NotEmpty String postId,
                                                                    @RequestParam(name = "page", required = false, defaultValue = "0") @Valid @Min(0) Integer page,
                                                                    @RequestParam(name = "pageSize", required = false, defaultValue = "20") @Valid @Min(0) int pageSize,
                                                                    @RequestParam(name = "sortBy", required = false, defaultValue = "created") @Valid @CommentSort String sortBy,
                                                                    @RequestParam(name = "sortOrder", required = false, defaultValue = "desc") String sortOrder) {
        //TODO refactor to command with paging
        List<CommentQueryResult> postComments = commentService.getPostComments(postId);
        return ResponseEntity.ok(postComments);
    }

    //get all user's likes
    @GetMapping(value = "/like/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommentQueryResult>> getUserLikedComments(@PathVariable @Valid @NotEmpty String userId) {
        List<CommentQueryResult> commentsLikedByUser = commentService.getCommentsLikedByUser(userId);
        return ResponseEntity.ok(commentsLikedByUser);
    }

    //getRandomComment
    @GetMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentQueryResult> getRandomComment() throws CommentNotExistException {
        Optional<CommentQueryResult> comment = commentService.getRandomPost();
        if (comment.isPresent()) {
            return ResponseEntity.ok(comment.get());
        } else {
            throw new CommentNotExistException("No comment found");
        }
    }
}
