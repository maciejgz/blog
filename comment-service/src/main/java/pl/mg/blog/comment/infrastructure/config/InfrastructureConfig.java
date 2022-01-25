package pl.mg.blog.comment.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.mg.blog.comment.core.port.incoming.AddComment;
import pl.mg.blog.comment.core.port.incoming.DislikeComment;
import pl.mg.blog.comment.core.port.incoming.GetComment;
import pl.mg.blog.comment.core.port.incoming.LikeComment;
import pl.mg.blog.comment.core.port.outgoing.*;
import pl.mg.blog.comment.core.service.CommentFacade;
import pl.mg.blog.comment.infrastructure.adapter.CommentDatabaseInMemoryAdapter;
import pl.mg.blog.comment.infrastructure.adapter.CommentEventKafkaPublisher;

@Component
public class InfrastructureConfig {

    //incoming ports
    @Bean
    public AddComment addComment(CheckBlacklist checkBlacklist, CheckPostExistence checkPostExistence,
                                 CheckUserExistence checkUserExistence, GetPostAuthor getPostAuthor,
                                 CommentDatabase commentDatabase, CommentEventPublisher commentEventPublisher) {
        return new CommentFacade(checkBlacklist, checkPostExistence, checkUserExistence, getPostAuthor,
                commentDatabase, commentEventPublisher);
    }


    @Bean
    public DislikeComment dislikeComment(CheckBlacklist checkBlacklist, CheckPostExistence checkPostExistence,
                                         CheckUserExistence checkUserExistence, GetPostAuthor getPostAuthor,
                                         CommentDatabase commentDatabase, CommentEventPublisher commentEventPublisher) {
        return new CommentFacade(checkBlacklist, checkPostExistence, checkUserExistence, getPostAuthor,
                commentDatabase, commentEventPublisher);
    }

    @Bean
    public LikeComment likeComment(CheckBlacklist checkBlacklist, CheckPostExistence checkPostExistence,
                                   CheckUserExistence checkUserExistence, GetPostAuthor getPostAuthor,
                                   CommentDatabase commentDatabase, CommentEventPublisher commentEventPublisher) {
        return new CommentFacade(checkBlacklist, checkPostExistence, checkUserExistence, getPostAuthor,
                commentDatabase, commentEventPublisher);
    }

    @Bean
    public GetComment getComment(CheckBlacklist checkBlacklist, CheckPostExistence checkPostExistence,
                                 CheckUserExistence checkUserExistence, GetPostAuthor getPostAuthor,
                                 CommentDatabase commentDatabase, CommentEventPublisher commentEventPublisher) {
        return new CommentFacade(checkBlacklist, checkPostExistence, checkUserExistence, getPostAuthor,
                commentDatabase, commentEventPublisher);
    }


    //outgoing ports
    @Bean
    public CommentDatabase commentDatabase() {
        return new CommentDatabaseInMemoryAdapter();
    }

    @Bean
    public CommentEventPublisher commentEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        return new CommentEventKafkaPublisher(kafkaTemplate);
    }
}
