package pl.mg.blog.comment.service;

import pl.mg.blog.comment.exception.*;
import pl.mg.blog.comment.model.*;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    CommentQueryResult addComment(AddCommentCommand command) throws PostNotFoundException, UserNotFoundException;

    LikeCommentResponse likeComment(LikeCommentCommand command)
            throws CommentNotExistException, CommentAlreadyLikedException, UserNotFoundException;

    DislikeCommentResponse dislikeComment(DislikeCommentCommand command)
            throws CommentNotExistException, CommentAlreadyDislikedException, UserNotFoundException;

    CommentQueryResult getComment(String commentId) throws CommentNotExistException;

    CommentQueryPageResult getUserComments(GetCommentsByUserIdCommand command) throws UserNotFoundException;

    CommentQueryPageResult getPostComments(GetCommentsByPostIdCommand command) throws PostNotFoundException;

    List<CommentQueryResult> getCommentsLikedByUser(String username) throws UserNotFoundException;

    Optional<CommentQueryResult> getRandomComment();
}
