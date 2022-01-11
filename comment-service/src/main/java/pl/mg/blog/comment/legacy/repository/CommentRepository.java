package pl.mg.blog.comment.legacy.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CommentRepository {
    Comment save(Comment comment);

    Comment getRandomComment();

    Optional<Comment> findById(String commentId);

    List<Comment> findAllByPostId(String postId);

    List<Comment> findAllByAuthor(String username);

    Set<Comment> findAllByLikesUsername(String username);
}
