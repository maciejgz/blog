package pl.mg.blog.comment.core.port.incoming;


import pl.mg.blog.comment.core.model.command.LikeCommentCommand;

public interface LikeComment {

    void likeComment(LikeCommentCommand command);
}
