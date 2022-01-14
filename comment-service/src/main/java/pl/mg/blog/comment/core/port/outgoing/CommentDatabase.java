package pl.mg.blog.comment.core.port.outgoing;

import pl.mg.blog.comment.core.model.Comment;

import java.util.Optional;

public interface CommentDatabase {
    Optional<Comment> getComment(String id);

    void save(Comment newComment);
}
