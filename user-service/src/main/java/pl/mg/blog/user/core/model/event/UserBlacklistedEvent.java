package pl.mg.blog.user.core.model.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class UserBlacklistedEvent extends Event {

    String user;

    String blacklistedUser;
}
