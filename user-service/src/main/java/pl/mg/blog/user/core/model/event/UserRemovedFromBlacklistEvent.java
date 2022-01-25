package pl.mg.blog.user.core.model.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class UserRemovedFromBlacklistEvent extends Event {

    String user;
    String userRemovedFromBlacklist;
}
