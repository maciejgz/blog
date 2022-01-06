package pl.mg.blog.user.core.model.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public abstract class Event {

    private final UUID id = UUID.randomUUID();
    private final Instant created = Instant.now();

}
