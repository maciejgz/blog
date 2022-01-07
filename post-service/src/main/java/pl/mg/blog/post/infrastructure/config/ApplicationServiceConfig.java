package pl.mg.blog.post.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.mg.blog.post.core.port.incoming.*;
import pl.mg.blog.post.core.port.outgoing.PostDatabase;
import pl.mg.blog.post.core.port.outgoing.PostEventPublisher;
import pl.mg.blog.post.core.service.PostDomainService;
import pl.mg.blog.post.infrastructure.adapter.InMemoryPostDatabaseAdapter;
import pl.mg.blog.post.infrastructure.adapter.PostEventKafkaPublisher;

/**
 * All beans configuration should be here.
 */
@Component
public class ApplicationServiceConfig {

    @Bean
    public CreatePost getCreatePost(PostDatabase database, PostEventPublisher postEventPublisher) {
        return new PostDomainService(database, postEventPublisher);
    }

    @Bean
    public EditPost getEditPost(PostDatabase database, PostEventPublisher postEventPublisher) {
        return new PostDomainService(database, postEventPublisher);
    }

    @Bean
    public GetPost getGetPost(PostDatabase database, PostEventPublisher postEventPublisher) {
        return new PostDomainService(database, postEventPublisher);
    }

    @Bean
    public LikePost getLikePost(PostDatabase database, PostEventPublisher postEventPublisher) {
        return new PostDomainService(database, postEventPublisher);
    }

    @Bean
    public RemovePostLike getRemovePostLike(PostDatabase database, PostEventPublisher postEventPublisher) {
        return new PostDomainService(database, postEventPublisher);
    }

    @Bean
    public PostDatabase getInMemoryPostDatabase() {
        return new InMemoryPostDatabaseAdapter();
    }

    @Bean
    public PostEventPublisher getEventPublisher() {
        return new PostEventKafkaPublisher();
    }

}
