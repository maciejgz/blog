package pl.mg.blog.comment.infrastructure.adapter;

import pl.mg.blog.comment.core.model.Comment;
import pl.mg.blog.comment.core.port.outgoing.CommentDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentDatabaseInMemoryAdapter implements CommentDatabase {

    private static final List<Comment> COMMENTS = new ArrayList<>();

    @Override
    public Optional<Comment> getComment(String id) {
        return COMMENTS.stream().filter(comment -> comment.getCommentId().toString().equals(id)).findFirst();
    }

    @Override
    public void save(Comment newComment) {
        if (COMMENTS.stream().anyMatch(comment -> comment.getCommentId().equals(newComment.getCommentId()))) {
            COMMENTS.removeIf(comment -> comment.getCommentId().equals(newComment.getCommentId()));
        }
        COMMENTS.add(newComment);
    }


}
