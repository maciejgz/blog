package pl.mg.blog.user.infrastructure.adapter;

import lombok.extern.slf4j.Slf4j;
import pl.mg.blog.user.core.model.event.UserBlacklistedEvent;
import pl.mg.blog.user.core.model.event.UserRegisteredEvent;
import pl.mg.blog.user.core.model.event.UserRemovedFromBlacklistEvent;
import pl.mg.blog.user.core.port.outgoing.UserEventPublisher;

/**
 * Publisher of Kafka events
 */
@Slf4j
public class UserKafkaEventPublisher implements UserEventPublisher {

    @Override
    public void publishUserRegisteredEvent(UserRegisteredEvent event) {
        log.debug("publishUserRegisteredEvent");
        //TODO implement
    }

    @Override
    public void publishUserBlacklistedEvent(UserBlacklistedEvent event) {
        log.debug("publishUserBlacklistedEvent");
        //TODO implement
    }

    @Override
    public void publishUserRemovedFromBlacklist(UserRemovedFromBlacklistEvent event) {
        log.debug("publishUserRemovedFromBlacklist");
        //TODO implement
    }
}
