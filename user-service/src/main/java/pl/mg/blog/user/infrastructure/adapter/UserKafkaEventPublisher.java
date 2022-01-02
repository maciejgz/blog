package pl.mg.blog.user.infrastructure.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import pl.mg.blog.user.core.model.event.UserBlacklistedEvent;
import pl.mg.blog.user.core.model.event.UserRegisteredEvent;
import pl.mg.blog.user.core.model.event.UserRemovedFromBlacklistEvent;
import pl.mg.blog.user.core.port.outgoing.UserEventPublisher;
import pl.mg.blog.user.infrastructure.KafkaTopicConfig;

import java.util.UUID;

/**
 * Publisher of Kafka events
 */
@Slf4j
public class UserKafkaEventPublisher implements UserEventPublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishUserRegisteredEvent(UserRegisteredEvent event) {
        log.debug("publishUserRegisteredEvent");
        kafkaTemplate.send(KafkaTopicConfig.BLOG_USER_TOPIC, UUID.randomUUID().toString(), event);
    }

    @Override
    public void publishUserBlacklistedEvent(UserBlacklistedEvent event) {
        log.debug("publishUserBlacklistedEvent");
        kafkaTemplate.send(KafkaTopicConfig.BLOG_USER_TOPIC, UUID.randomUUID().toString(), event);
    }

    @Override
    public void publishUserRemovedFromBlacklist(UserRemovedFromBlacklistEvent event) {
        log.debug("publishUserRemovedFromBlacklist");
        kafkaTemplate.send(KafkaTopicConfig.BLOG_USER_TOPIC, UUID.randomUUID().toString(), event);
    }
}
