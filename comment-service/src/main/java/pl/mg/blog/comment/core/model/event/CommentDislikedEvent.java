package pl.mg.blog.comment.core.model.event;

import lombok.Value;

@Value
public class CommentDislikedEvent extends Event {

    String postId;
    String commentId;
    String username;

}
