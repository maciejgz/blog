package pl.mg.blog.comment.core.model.event;

import lombok.Value;

@Value
public class CommentAddedEvent extends Event {

    String postId;
    String username;
    String commentContent;

}
