package pl.mg.blog.post;

import java.util.Optional;

public interface PostCustomRepository {

    Optional<Post> getRandomPost();

}
