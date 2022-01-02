package pl.mg.blog.user.core.port.outgoing;

import pl.mg.blog.user.core.model.event.UserBlacklistedEvent;
import pl.mg.blog.user.core.model.event.UserRegisteredEvent;
import pl.mg.blog.user.core.model.event.UserRemovedFromBlacklistEvent;

/**
 * Outgoing port for publishing user related events.
 */
public interface UserEventPublisher {

    void publishUserRegisteredEvent(UserRegisteredEvent event);

    void publishUserBlacklistedEvent(UserBlacklistedEvent event);

    void publishUserRemovedFromBlacklist(UserRemovedFromBlacklistEvent event);

}
