package pl.mg.blog.comment.core.port.incoming;

import pl.mg.blog.comment.core.model.command.DislikeCommentCommand;

public interface DislikeComment {

    void dislikeComment(DislikeCommentCommand command);
}
