package pl.mg.blog.comment.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class LocalCommentRepository implements CommentRepository {

    private static final Map<String, Comment> COMMENTS = Collections.synchronizedMap(new HashMap<>());


    @Override
    public Comment save(Comment comment) {
        COMMENTS.put(comment.getId(), comment);
        return comment;
    }

    @Override
    public Comment getRandomComment() {
        Random random = new SecureRandom();
        int index = random.nextInt(COMMENTS.size());
        return new ArrayList<>(COMMENTS.values()).get(index);
    }

    @Override
    public Optional<Comment> findById(String commentId) {
        return Optional.ofNullable(COMMENTS.get(commentId));
    }

    @Override
    public List<Comment> findAllByPostId(String postId) {
        return COMMENTS.values().stream().filter(comment -> comment.getPostId().equalsIgnoreCase(postId)).collect(Collectors.toList());
    }

    @Override
    public List<Comment> findAllByAuthor(String username) {
        return COMMENTS.values().stream().filter(comment -> comment.getAuthor().equalsIgnoreCase(username)).collect(Collectors.toList());
    }

    @Override
    public Set<Comment> findAllByLikesUsername(String username) {
        return COMMENTS.values().stream()
                .filter(comment ->
                        comment.getLikes().stream().anyMatch(like -> like.getUsername().equalsIgnoreCase(username)))
                .collect(Collectors.toSet());
    }
}
