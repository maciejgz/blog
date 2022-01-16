package pl.mg.blog.comment.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String BLOG_COMMENT_TOPIC = "blog-comment-topic";

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public NewTopic commentTopicConfig() {
        return TopicBuilder.name(BLOG_COMMENT_TOPIC)
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }

}
