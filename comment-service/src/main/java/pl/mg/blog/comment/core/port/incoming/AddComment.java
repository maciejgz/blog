package pl.mg.blog.comment.core.port.incoming;

import pl.mg.blog.comment.core.model.Comment;
import pl.mg.blog.comment.core.model.command.AddCommentCommand;

public interface AddComment {

    public Comment addComment(AddCommentCommand command);

}
