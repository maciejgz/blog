package pl.mg.blog.legacy.post.repository;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post add(Post post);

    Optional<Post> get(String id);

    List<Post> findByUsername(String username);

    Post getRandomPost();

    List<Post> getAll();
}
