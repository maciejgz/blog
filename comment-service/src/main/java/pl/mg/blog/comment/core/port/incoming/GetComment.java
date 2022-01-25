package pl.mg.blog.comment.core.port.incoming;

import pl.mg.blog.comment.core.model.Comment;
import pl.mg.blog.comment.core.model.exception.CommentNotExistException;

public interface GetComment {

    public Comment getComment(String commentId) throws CommentNotExistException;
}
