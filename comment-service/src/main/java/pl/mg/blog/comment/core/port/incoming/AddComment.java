package pl.mg.blog.comment.core.port.incoming;

import pl.mg.blog.comment.core.model.Comment;
import pl.mg.blog.comment.core.model.command.AddCommentCommand;
import pl.mg.blog.comment.core.model.exception.PostNotFoundException;
import pl.mg.blog.comment.core.model.exception.UserBlacklistedException;
import pl.mg.blog.comment.core.model.exception.UserNotFoundException;

public interface AddComment {

    public Comment addComment(AddCommentCommand command) throws UserNotFoundException, PostNotFoundException, UserBlacklistedException;

}
