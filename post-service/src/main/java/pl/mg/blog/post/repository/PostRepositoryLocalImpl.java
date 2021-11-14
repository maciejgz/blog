package pl.mg.blog.post.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class PostRepositoryLocalImpl implements PostRepository {

    private static final Map<String, Post> POSTS = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Post add(Post post) {
        POSTS.put(post.getId(), post);
        return post;
    }

    @Override
    public Optional<Post> get(String id) {
        return Optional.ofNullable(POSTS.get(id));
    }

    @Override
    public List<Post> findByUsername(String username) {
        return POSTS.values().stream().filter(post -> username.equalsIgnoreCase(post.getAuthor())).collect(Collectors.toList());
    }

    @Override
    public Post getRandomPost() {
        Random random = new SecureRandom();
        int index = random.nextInt(POSTS.size());
        return new ArrayList<>(POSTS.values()).get(index);
    }

    @Override
    public List<Post> getAll() {
        return new ArrayList<>(POSTS.values());
    }

}
