package pl.mg.blog.post.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.mg.blog.post.core.port.incoming.*;
import pl.mg.blog.post.core.port.outgoing.PostDatabase;
import pl.mg.blog.post.core.port.outgoing.PostEventPublisher;
import pl.mg.blog.post.core.port.outgoing.UserServiceClient;
import pl.mg.blog.post.core.service.PostDomainService;
import pl.mg.blog.post.infrastructure.adapter.InMemoryPostDatabaseAdapter;
import pl.mg.blog.post.infrastructure.adapter.PostEventKafkaPublisher;

/**
 * All beans configuration should be here.
 */
@Component
public class ApplicationServiceConfig {

    //incoming adapters
    @Bean
    public CreatePost createPost(PostDatabase database, PostEventPublisher postEventPublisher, UserServiceClient userServiceClient) {
        return new PostDomainService(database, postEventPublisher, userServiceClient);
    }

    @Bean
    public EditPost editPost(PostDatabase database, PostEventPublisher postEventPublisher, UserServiceClient userServiceClient) {
        return new PostDomainService(database, postEventPublisher, userServiceClient);
    }

    @Bean
    public GetPost getPost(PostDatabase database, PostEventPublisher postEventPublisher, UserServiceClient userServiceClient) {
        return new PostDomainService(database, postEventPublisher, userServiceClient);
    }

    @Bean
    public LikePost likePost(PostDatabase database, PostEventPublisher postEventPublisher, UserServiceClient userServiceClient) {
        return new PostDomainService(database, postEventPublisher, userServiceClient);
    }

    @Bean
    public RemovePostLike removePostLike(PostDatabase database, PostEventPublisher postEventPublisher, UserServiceClient userServiceClient) {
        return new PostDomainService(database, postEventPublisher, userServiceClient);
    }


    //outgoing adapters
    @Bean
    public PostDatabase getInMemoryPostDatabase() {
        return new InMemoryPostDatabaseAdapter();
    }

    @Bean
    public PostEventPublisher getEventPublisher() {
        return new PostEventKafkaPublisher();
    }

}
