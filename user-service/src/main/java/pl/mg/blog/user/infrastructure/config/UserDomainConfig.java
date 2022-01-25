package pl.mg.blog.user.infrastructure.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.mg.blog.user.core.port.incoming.*;
import pl.mg.blog.user.core.port.outgoing.UserDatabase;
import pl.mg.blog.user.core.port.outgoing.UserEventPublisher;
import pl.mg.blog.user.core.service.UserService;
import pl.mg.blog.user.infrastructure.adapter.UserInMemoryRepository;
import pl.mg.blog.user.infrastructure.adapter.UserKafkaEventPublisher;

@Component
public class UserDomainConfig {

    //incoming ports
    @Bean
    public GetUser getUser(UserDatabase userDatabase, UserEventPublisher eventPublisher) {
        return new UserService(userDatabase, eventPublisher);
    }

    @Bean
    public RegisterUser registerUser(UserDatabase userDatabase, UserEventPublisher eventPublisher) {
        return new UserService(userDatabase, eventPublisher);
    }

    @Bean
    public BlacklistUser blacklistUser(UserDatabase userDatabase, UserEventPublisher eventPublisher) {
        return new UserService(userDatabase, eventPublisher);
    }

    @Bean
    public RemoveUserFromBlacklist removeUserFromBlacklist(UserDatabase userDatabase, UserEventPublisher eventPublisher) {
        return new UserService(userDatabase, eventPublisher);
    }

    @Bean
    public CheckIfUserIsBlacklisted checkIfUserIsBlacklisted(UserDatabase userDatabase, UserEventPublisher eventPublisher) {
        return new UserService(userDatabase, eventPublisher);
    }

    //outgoing ports
    @Bean
    @Qualifier("userDatabase")
    public UserDatabase userDatabase() {
        return new UserInMemoryRepository();
    }

    @Bean
    @Qualifier("userEventPublisher")
    public UserEventPublisher userEventPublisher() {
        return new UserKafkaEventPublisher();
    }

}
