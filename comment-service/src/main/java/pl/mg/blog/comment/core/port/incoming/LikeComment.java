package pl.mg.blog.comment.core.port.incoming;


import pl.mg.blog.comment.core.model.command.LikeCommentCommand;
import pl.mg.blog.comment.core.model.exception.CommentAlreadyLikedException;
import pl.mg.blog.comment.core.model.exception.CommentNotExistException;
import pl.mg.blog.comment.core.model.exception.UserBlacklistedException;
import pl.mg.blog.comment.core.model.exception.UserNotFoundException;

public interface LikeComment {

    void likeComment(LikeCommentCommand command) throws CommentNotExistException, UserBlacklistedException,
            CommentAlreadyLikedException, UserNotFoundException;

}
