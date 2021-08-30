package pl.mg.blog.comment;

import java.util.Optional;

public interface CommentCustomRepository {

    Optional<Comment> getRandomComment();

}
