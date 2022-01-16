package pl.mg.blog.comment.infrastructure.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.mg.blog.comment.core.model.event.CommentAddedEvent;
import pl.mg.blog.comment.core.model.event.CommentDislikedEvent;
import pl.mg.blog.comment.core.model.event.CommentLikedEvent;
import pl.mg.blog.comment.core.port.outgoing.CommentEventPublisher;
import pl.mg.blog.comment.infrastructure.config.KafkaTopicConfig;

import java.util.UUID;

@Slf4j
@Service
public class CommentEventKafkaPublisher implements CommentEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CommentEventKafkaPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishCommentAddedEvent(CommentAddedEvent commentAddedEvent) {
        log.debug("publishCommentAddedEvent");
        kafkaTemplate.send(KafkaTopicConfig.BLOG_COMMENT_TOPIC, UUID.randomUUID().toString(), commentAddedEvent);
    }

    @Override
    public void publishCommentLikedEvent(CommentLikedEvent commentLikedEvent) {
        log.debug("publishCommentLikedEvent");
        kafkaTemplate.send(KafkaTopicConfig.BLOG_COMMENT_TOPIC, UUID.randomUUID().toString(), commentLikedEvent);
    }

    @Override
    public void publishCommentDislikedEvent(CommentDislikedEvent commentDislikedEvent) {
        log.debug("publishCommentDislikedEvent");
        kafkaTemplate.send(KafkaTopicConfig.BLOG_COMMENT_TOPIC, UUID.randomUUID().toString(), commentDislikedEvent);
    }
}
