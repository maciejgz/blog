package pl.mg.blog.comment.core.port.incoming;

import pl.mg.blog.comment.core.model.command.DislikeCommentCommand;
import pl.mg.blog.comment.core.model.exception.CommentAlreadyDislikedException;
import pl.mg.blog.comment.core.model.exception.CommentNotExistException;
import pl.mg.blog.comment.core.model.exception.UserBlacklistedException;
import pl.mg.blog.comment.core.model.exception.UserNotFoundException;

public interface DislikeComment {

    void dislikeComment(DislikeCommentCommand command) throws UserNotFoundException, CommentNotExistException, UserBlacklistedException, CommentAlreadyDislikedException;
}
