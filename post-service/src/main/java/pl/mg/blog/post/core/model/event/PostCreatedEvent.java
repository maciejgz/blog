package pl.mg.blog.post.core.model.event;


import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class PostCreatedEvent extends Event {

    UUID postId;
    String author;
    Instant postCreatedDate;

}
