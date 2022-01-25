package pl.mg.blog.post.core.model.event;

import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
public abstract class Event {

    private final UUID id = UUID.randomUUID();
    private final Instant created = Instant.now();

}
