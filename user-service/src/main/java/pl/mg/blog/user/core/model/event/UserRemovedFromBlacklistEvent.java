package pl.mg.blog.user.core.model.event;

import lombok.Value;

import java.time.Instant;

@Value
public class UserRemovedFromBlacklistEvent {

    String user;
    String userRemovedFromBlacklist;
    Instant timestamp;
}
