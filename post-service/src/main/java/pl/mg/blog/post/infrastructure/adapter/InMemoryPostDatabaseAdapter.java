package pl.mg.blog.post.infrastructure.adapter;

import pl.mg.blog.post.core.model.aggregate.Post;
import pl.mg.blog.post.core.port.outgoing.PostDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryPostDatabaseAdapter implements PostDatabase {

    private static final List<Post> POSTS = new ArrayList<>();

    @Override
    public Optional<Post> getPost(String id) {
        return POSTS.stream().filter(post -> post.getId().toString().equals(id)).findFirst();
    }

    @Override
    public void save(Post newPost) {
        if (POSTS.stream().anyMatch(post -> post.getId().equals(newPost.getId()))) {
            POSTS.removeIf(post -> post.getId().equals(newPost.getId()));
        }
        POSTS.add(newPost);
    }


}
