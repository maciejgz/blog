package pl.mg.blog.comment.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mg.blog.comment.application.model.CommentQueryPageResult;
import pl.mg.blog.comment.application.model.CommentQueryResult;
import pl.mg.blog.comment.application.model.CommentSort;
import pl.mg.blog.comment.application.service.CommentApplicationService;
import pl.mg.blog.comment.core.model.command.AddCommentCommand;
import pl.mg.blog.comment.core.model.command.DislikeCommentCommand;
import pl.mg.blog.comment.core.model.command.LikeCommentCommand;
import pl.mg.blog.comment.core.model.exception.CommentAlreadyDislikedException;
import pl.mg.blog.comment.core.model.exception.CommentAlreadyLikedException;
import pl.mg.blog.comment.core.model.exception.CommentNotExistException;
import pl.mg.blog.comment.core.model.exception.PostNotFoundException;
import pl.mg.blog.comment.core.model.exception.UserBlacklistedException;
import pl.mg.blog.comment.core.model.exception.UserNotFoundException;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping(value = "/comment")
@Slf4j
public class CommentController {

    private final CommentApplicationService commentApplicationService;

    public CommentController(CommentApplicationService commentApplicationService) {
        this.commentApplicationService = commentApplicationService;
    }

    @PostMapping(value = "")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentQueryResult> addComment(@Valid @RequestBody AddCommentCommand command)
            throws UserNotFoundException, PostNotFoundException, UserBlacklistedException {
        CommentQueryResult commentQueryResult = commentApplicationService.addComment(command);
        return ResponseEntity.ok(commentQueryResult);
    }

    @PutMapping(value = "/like")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> likeComment(@Valid @RequestBody LikeCommentCommand command)
            throws UserNotFoundException, UserBlacklistedException, CommentAlreadyLikedException, CommentNotExistException {
        commentApplicationService.likeComment(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/dislike")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> dislikeComment(@Valid @RequestBody DislikeCommentCommand command)
            throws UserNotFoundException, UserBlacklistedException, CommentNotExistException, CommentAlreadyDislikedException {
        commentApplicationService.dislikeComment(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentQueryResult> getRandomComment() throws CommentNotExistException {
        //TODO implement
       /* Optional<CommentQueryResult> comment = commentService.getRandomComment();
        if (comment.isPresent()) {
            return ResponseEntity.ok(comment.get());
        } else {
            throw new CommentNotExistException("No comment found");
        }*/
        return ResponseEntity.ok(null);
    }

    //get by comment id
    @GetMapping(value = "/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentQueryResult> getComment(@PathVariable @Valid @NotNull String commentId)
            throws pl.mg.blog.comment.core.model.exception.CommentNotExistException {
        CommentQueryResult comment = commentApplicationService.getComment(commentId);
        return ResponseEntity.ok(comment);
    }

    //get all user comments
    @GetMapping(value = "/user/{username}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentQueryPageResult> getUserComments(@PathVariable @Valid @NotEmpty String username,
            @RequestParam(name = "page", required = false, defaultValue = "0") @Valid @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @Valid @Min(0) int pageSize,
            @RequestParam(name = "sortBy", required = false,
                          defaultValue = "created") @Valid @CommentSort String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "desc") String sortOrder
    ) throws UserNotFoundException {
        //TODO implement
        return null;
       /* GetCommentsByUserIdCommand command = new GetCommentsByUserIdCommand();
        command.setUsername(username);
        QueryPageableCommand queryPageableCommand = new QueryPageableCommand(page, pageSize, sortBy, sortOrder);
        command.setPageableCommand(queryPageableCommand);
        CommentQueryPageResult userComments = commentService.getUserComments(command);
        return ResponseEntity.ok(userComments);*/
    }

    //get all comments for post
    @GetMapping(value = "/post/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentQueryPageResult> getPostComments(@PathVariable @Valid @NotEmpty String postId,
            @RequestParam(name = "page", required = false, defaultValue = "0") @Valid @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @Valid @Min(0) int pageSize,
            @RequestParam(name = "sortBy", required = false,
                          defaultValue = "created") @Valid @CommentSort String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "desc") String sortOrder) {
        //TODO implement
        return null;
        /*  GetCommentsByPostIdCommand command = new GetCommentsByPostIdCommand();
        command.setPostId(postId);
        QueryPageableCommand queryPageableCommand = new QueryPageableCommand(page, pageSize, sortBy, sortOrder);
        command.setPageableCommand(queryPageableCommand);
        CommentQueryPageResult postComments = commentService.getPostComments(command);
        return ResponseEntity.ok(postComments);*/
    }

    //get all user's likes
    @GetMapping(value = "/like/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommentQueryResult>> getUserLikedComments(@PathVariable @Valid @NotEmpty String userId)
            throws UserNotFoundException {
        //TODO implement
        return null;
     /*   List<CommentQueryResult> commentsLikedByUser = commentService.getCommentsLikedByUser(userId);
        return ResponseEntity.ok(commentsLikedByUser);*/
    }

}
