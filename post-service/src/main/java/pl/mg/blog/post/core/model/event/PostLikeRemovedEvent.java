package pl.mg.blog.post.core.model.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class PostLikeRemovedEvent extends Event {

    String postId;
    String username;

}
