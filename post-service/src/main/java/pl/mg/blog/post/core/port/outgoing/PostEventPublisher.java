package pl.mg.blog.post.core.port.outgoing;

import pl.mg.blog.post.core.model.event.PostCreatedEvent;
import pl.mg.blog.post.core.model.event.PostLikeRemovedEvent;
import pl.mg.blog.post.core.model.event.PostLikedEvent;
import pl.mg.blog.post.core.model.event.PostUpdatedEvent;

public interface PostEventPublisher {

    void publishPostCreatedEvent(PostCreatedEvent postCreatedEvent);

    void publishPostUpdatedEvent(PostUpdatedEvent postUpdatedEvent);

    void publishPostLikedEvent(PostLikedEvent postLikedEvent);

    void publishPostLikeRemovedEvent(PostLikeRemovedEvent postLikeRemovedEvent);
}
