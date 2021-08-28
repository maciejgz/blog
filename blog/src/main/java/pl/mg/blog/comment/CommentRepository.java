package pl.mg.blog.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    Set<Comment> findAllByAuthor(String author);

    Set<Comment> findAllByPostId(String author);

    Set<Comment> findAllByLikesUsername(String username);
}
