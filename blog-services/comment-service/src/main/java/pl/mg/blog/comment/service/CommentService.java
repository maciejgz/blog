package pl.mg.blog.comment.service;

import pl.mg.blog.comment.exception.CommentAlreadyDislikedException;
import pl.mg.blog.comment.exception.CommentAlreadyLikedException;
import pl.mg.blog.comment.exception.CommentNotExistException;
import pl.mg.blog.comment.model.*;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    CommentQueryResult addComment(AddCommentCommand command);

    LikeCommentResponse likeComment(LikeCommentCommand command)
            throws CommentNotExistException, CommentAlreadyLikedException;

    DislikeCommentResponse dislikeComment(DislikeCommentCommand command)
            throws CommentNotExistException, CommentAlreadyDislikedException;

    CommentQueryResult getComment(String commentId) throws CommentNotExistException;

    CommentQueryPageResult getUserComments(GetCommentsByUserIdCommand command);

    CommentQueryPageResult getPostComments(GetCommentsByPostIdCommand command);

    List<CommentQueryResult> getCommentsLikedByUser(String username);

    Optional<CommentQueryResult> getRandomComment();
}
