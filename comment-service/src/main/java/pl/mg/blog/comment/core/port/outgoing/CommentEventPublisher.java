package pl.mg.blog.comment.core.port.outgoing;

import pl.mg.blog.comment.core.model.event.CommentAddedEvent;
import pl.mg.blog.comment.core.model.event.CommentDislikedEvent;
import pl.mg.blog.comment.core.model.event.CommentLikedEvent;

public interface CommentEventPublisher {

    void publishCommentAddedEvent(CommentAddedEvent commentAddedEvent);

    void publishCommentLikedEvent(CommentLikedEvent commentLikedEvent);

    void publishCommentDislikedEvent(CommentDislikedEvent commentDislikedEvent);
}
