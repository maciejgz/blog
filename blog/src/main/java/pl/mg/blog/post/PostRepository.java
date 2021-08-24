package pl.mg.blog.post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findAllByAuthor(String username);

    Optional<Post> findFirstByCommentIdsContains(String commentId);

    Optional<Post> findByAuthorAndId(String author, String id);
}
