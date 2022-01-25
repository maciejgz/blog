package pl.mg.blog.post.core.model.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mg.blog.post.core.model.command.CreatePostCommand;
import pl.mg.blog.post.core.model.command.EditPostCommand;
import pl.mg.blog.post.core.model.command.LikePostCommand;
import pl.mg.blog.post.core.model.command.RemovePostLikeCommand;
import pl.mg.blog.post.core.model.exception.PostAlreadyLikedException;
import pl.mg.blog.post.core.model.exception.PostAlreadyNotLikedException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Post aggregate
 */
@AllArgsConstructor
@Getter
public class Post {

    private UUID id;

    private String author;

    private String title;

    private String content;

    private Instant created;

    private Instant updatedAt;

    private List<String> likedByUsers;

    private List<String> commentIds;

    public static Post ofCreatePostCommand(CreatePostCommand createPostCommand) {
        return new Post(UUID.randomUUID(),
                createPostCommand.getUsername(),
                createPostCommand.getTitle(),
                createPostCommand.getContent(),
                Instant.now(),
                Instant.now(),
                new ArrayList<>(),
                new ArrayList<>());
    }

    public void editPost(EditPostCommand command) {
        this.content = command.getContent();
        this.title = command.getTitle();
        this.updatedAt = Instant.now();
    }

    public void likePost(LikePostCommand command) throws PostAlreadyLikedException {
        if (isLiked(command.getUsername())) {
            throw new PostAlreadyLikedException("Post " + this.getId().toString() + "is already liked by " + command.getUsername());
        }
        this.likedByUsers.add(command.getUsername());
    }

    public void removePostLike(RemovePostLikeCommand command) throws PostAlreadyNotLikedException {
        if (!isLiked(command.getUsername())) {
            throw new PostAlreadyNotLikedException("Post " + this.getId().toString() + "is already liked by " + command.getUsername());
        }
        this.likedByUsers.remove(command.getUsername());
    }

    private boolean isLiked(String username) {
        return this.getLikedByUsers().stream().anyMatch(s -> s.equals(username));
    }

}
