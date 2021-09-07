package pl.mg.blog.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String>, CommentCustomRepository {

    Set<Comment> findAllByAuthor(String author);

    @Override
    List<Comment> findAll();

    Page<Comment> findAllByAuthor(String author, Pageable pageable);

    Page<Comment> findAllByPostId(String postId, Pageable pageable);

    Set<Comment> findAllByLikesUsername(String username);
}
