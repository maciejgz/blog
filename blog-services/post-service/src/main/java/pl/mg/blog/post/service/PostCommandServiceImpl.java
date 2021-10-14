package pl.mg.blog.post.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.mg.blog.post.dto.*;
import pl.mg.blog.post.repository.Post;
import pl.mg.blog.post.repository.PostRepository;
import pl.mg.blog.post.user.client.UserClient;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Validated
public class PostCommandServiceImpl implements PostCommandService {

    private final PostRepository postRepository;
    private final UserClient userClient;

    public PostCommandServiceImpl(PostRepository postRepository, UserClient userClient) {
        this.postRepository = postRepository;
        this.userClient = userClient;
    }

    @Override
    public PostQueryResult createPost(@Valid CreatePostCommand command) throws UserNotFoundException {
        log.debug("createPost() called with: command = [" + command + "]");
        verifyIfUserExist(command.getUsername());
        Post post = new Post(UUID.randomUUID().toString(), command.getUsername(), command.getTitle(), command.getContent(),
                Instant.now(), null, 0L, null);
        return PostQueryResult.ofPost(postRepository.add(post));
    }

    @Override
    public void editPost(@Valid EditPostCommand command) throws PostNotFoundException, UserNotFoundException {
        verifyIfUserExist(command.getUsername());
        Optional<Post> post = postRepository.get(command.getId());
        if (post.isPresent()) {
            Post edit = post.get();
            if (edit.getAuthor().equals(command.getUsername())) {
                edit.setContent(command.getContent());
                edit.setTitle(command.getTitle());
                edit.setUpdatedAt(Instant.now());
                postRepository.add(edit);
            }
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    private void verifyIfUserExist(String username) throws UserNotFoundException {
        ResponseEntity<Void> response = userClient.checkIfUserExists(username);
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            throw new UserNotFoundException("User " + username + " not exists");
        }
    }
}
