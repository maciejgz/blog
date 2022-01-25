package pl.mg.blog.post.core.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.mg.blog.post.core.model.aggregate.Post;
import pl.mg.blog.post.core.model.command.*;
import pl.mg.blog.post.core.model.event.PostCreatedEvent;
import pl.mg.blog.post.core.model.event.PostLikeRemovedEvent;
import pl.mg.blog.post.core.model.event.PostLikedEvent;
import pl.mg.blog.post.core.model.event.PostUpdatedEvent;
import pl.mg.blog.post.core.model.exception.*;
import pl.mg.blog.post.core.port.incoming.*;
import pl.mg.blog.post.core.port.outgoing.PostDatabase;
import pl.mg.blog.post.core.port.outgoing.PostEventPublisher;
import pl.mg.blog.post.core.port.outgoing.UserServiceClient;
import pl.mg.blog.post.infrastructure.adapter.IsUserBlacklistedResponse;

import java.util.Optional;

public class PostDomainService implements CreatePost, EditPost, GetPost, LikePost, RemovePostLike {

    private final PostDatabase postDatabase;
    private final PostEventPublisher postEventPublisher;
    private final UserServiceClient userServiceClient;

    public PostDomainService(PostDatabase postDatabase, PostEventPublisher postEventPublisher, UserServiceClient userServiceClient) {
        this.postDatabase = postDatabase;
        this.postEventPublisher = postEventPublisher;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public Post createPost(CreatePostCommand command) throws UserNotFoundException {
        verifyIfUserExist(command.getUsername());
        Post post = Post.ofCreatePostCommand(command);
        postDatabase.save(post);
        postEventPublisher.publishPostCreatedEvent(new PostCreatedEvent(post.getId(), post.getAuthor(), post.getCreated()));
        return post;
    }

    @Override
    public Post editPost(EditPostCommand command) throws UserNotFoundException, PostNotFoundException {
        verifyIfUserExist(command.getUsername());
        Optional<Post> post = postDatabase.getPost(command.getId());
        if (post.isEmpty()) {
            throw new PostNotFoundException("Post " + command.getTitle() + " with ID " + command.getId() + " not exist");
        }
        Post postUpdated = post.get();
        postUpdated.editPost(command);
        postDatabase.save(postUpdated);
        postEventPublisher.publishPostUpdatedEvent(new PostUpdatedEvent(postUpdated.getId(), postUpdated.getAuthor(), postUpdated.getUpdatedAt()));
        return postUpdated;
    }

    @Override
    public Post getPost(GetPostCommand command) throws PostNotFoundException {
        Optional<Post> post = postDatabase.getPost(command.getId());
        return post.orElseThrow(() -> new PostNotFoundException("Post with ID " + command.getId() + " not exist"));
    }

    @Override
    public void likePost(LikePostCommand command) throws UserNotFoundException, PostNotFoundException, UserBlacklistedException,
            PostAlreadyLikedException {
        verifyIfUserExist(command.getUsername());
        Optional<Post> post = postDatabase.getPost(command.getPostId());
        if (post.isEmpty()) {
            throw new PostNotFoundException("Post with ID " + command.getPostId() + " not exist");
        }
        if (verifyIfUserIsBlacklisted(post.get().getAuthor(), command.getUsername())) {
            throw new UserBlacklistedException("User " + command.getUsername() + " is blacklisted by " + post.get().getAuthor());
        }
        post.get().likePost(command);
        postDatabase.save(post.get());
        postEventPublisher.publishPostLikedEvent(new PostLikedEvent(post.get().getId().toString(), command.getUsername()));
    }

    @Override
    public void removePostLikeCommand(RemovePostLikeCommand command) throws UserNotFoundException, PostNotFoundException, UserBlacklistedException, PostAlreadyNotLikedException {
        verifyIfUserExist(command.getUsername());
        Optional<Post> post = postDatabase.getPost(command.getPostId());
        if (post.isEmpty()) {
            throw new PostNotFoundException("Post with ID " + command.getPostId() + " not exist");
        }
        if (verifyIfUserIsBlacklisted(post.get().getAuthor(), command.getUsername())) {
            throw new UserBlacklistedException("User " + command.getUsername() + " is blacklisted by " + post.get().getAuthor());
        }
        post.get().removePostLike(command);
        postDatabase.save(post.get());
        postEventPublisher.publishPostLikeRemovedEvent(new PostLikeRemovedEvent(post.get().getId().toString(), command.getUsername()));
    }

    private void verifyIfUserExist(String username) throws UserNotFoundException {
        ResponseEntity<Void> response = userServiceClient.checkIfUserExists(username);
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            throw new UserNotFoundException("User " + username + " not exists");
        }
    }

    private boolean verifyIfUserIsBlacklisted(String username, String blacklistedUser) throws UserNotFoundException {
        ResponseEntity<IsUserBlacklistedResponse> response = userServiceClient.checkIfUserIsBlacklisted(username, blacklistedUser);
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            throw new UserNotFoundException("User " + blacklistedUser + " not exists");
        }
        IsUserBlacklistedResponse body = response.getBody();
        if (body == null) {
            throw new UserNotFoundException("User " + blacklistedUser + " not exists");
        }
        return body.isBlacklisted();
    }
}
