package pl.mg.blog.post.core.port.outgoing;

import pl.mg.blog.post.core.model.aggregate.Post;

import java.util.Optional;

public interface PostDatabase {
    Optional<Post> getPost(String id);

    void save(Post newPost);

    Optional<Post> getRandomPost();
}
