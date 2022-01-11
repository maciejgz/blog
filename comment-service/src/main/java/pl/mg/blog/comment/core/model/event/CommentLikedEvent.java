package pl.mg.blog.comment.core.model.event;

import lombok.Value;

@Value
public class CommentLikedEvent extends Event {

    String postId;
    String commentId;
    String username;

}
