package pl.mg.blog.post.infrastructure.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import pl.mg.blog.post.core.model.event.PostCreatedEvent;
import pl.mg.blog.post.core.model.event.PostLikeRemovedEvent;
import pl.mg.blog.post.core.model.event.PostLikedEvent;
import pl.mg.blog.post.core.model.event.PostUpdatedEvent;
import pl.mg.blog.post.core.port.outgoing.PostEventPublisher;
import pl.mg.blog.post.infrastructure.config.KafkaTopicConfig;

import java.util.UUID;

@Slf4j
public class PostEventKafkaPublisher implements PostEventPublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishPostCreatedEvent(PostCreatedEvent postCreatedEvent) {
        log.debug("publishEventCreatedEvent");
        kafkaTemplate.send(KafkaTopicConfig.BLOG_POST_TOPIC, UUID.randomUUID().toString(), postCreatedEvent);
    }

    @Override
    public void publishPostUpdatedEvent(PostUpdatedEvent postUpdatedEvent) {
        log.debug("publishPostUpdatedEvent");
        kafkaTemplate.send(KafkaTopicConfig.BLOG_POST_TOPIC, UUID.randomUUID().toString(), postUpdatedEvent);
    }

    @Override
    public void publishPostLikedEvent(PostLikedEvent postLikedEvent) {
        log.debug("publishPostLikedEvent");
        kafkaTemplate.send(KafkaTopicConfig.BLOG_POST_TOPIC, UUID.randomUUID().toString(), postLikedEvent);
    }

    @Override
    public void publishPostLikeRemovedEvent(PostLikeRemovedEvent postLikeRemovedEvent) {
        log.debug("publishPostLikeRemovedEvent");
        kafkaTemplate.send(KafkaTopicConfig.BLOG_POST_TOPIC, UUID.randomUUID().toString(), postLikeRemovedEvent);
    }
}
