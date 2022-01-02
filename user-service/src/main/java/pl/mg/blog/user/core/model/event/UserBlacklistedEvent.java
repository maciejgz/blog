package pl.mg.blog.user.core.model.event;

import lombok.Value;

import java.time.Instant;

@Value
public class UserBlacklistedEvent {

    String user;
    String blacklistedUser;
    Instant timestamp;
}
