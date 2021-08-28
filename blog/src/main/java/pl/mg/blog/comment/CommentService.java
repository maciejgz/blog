package pl.mg.blog.comment;

import pl.mg.blog.comment.exception.CommentAlreadyDislikedException;
import pl.mg.blog.comment.exception.CommentAlreadyLikedException;
import pl.mg.blog.comment.exception.CommentNotExistException;

import java.util.List;

public interface CommentService {

    public CommentQueryResult addComment(AddCommentCommand command);

    public LikeCommentResponse likeComment(LikeCommentCommand command) throws CommentNotExistException, CommentAlreadyLikedException;

    public DislikeCommentResponse dislikeComment(DislikeCommentCommand command) throws CommentNotExistException, CommentAlreadyDislikedException;

    public CommentQueryResult getComment(String commentId) throws CommentNotExistException;

    public List<CommentQueryResult> getUserComments(String username);

    public List<CommentQueryResult> getPostComments(String commentId);

    public List<CommentQueryResult> getCommentsLikedByUser(String username);

}
