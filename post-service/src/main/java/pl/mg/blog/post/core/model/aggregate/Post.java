package pl.mg.blog.post.core.model.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mg.blog.post.core.model.command.EditPostCommand;
import pl.mg.blog.post.core.model.command.LikePostCommand;

import java.time.Instant;
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

    public void editPost(EditPostCommand command) {
        //TODO implement
    }

    public void likePost(LikePostCommand command) {
        //TODO implement
    }

    public boolean isLiked(LikePostCommand command) {
        //TODO implement
        return false;
    }

}
