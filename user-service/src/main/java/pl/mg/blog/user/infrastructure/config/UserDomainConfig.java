package pl.mg.blog.user.infrastructure.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.mg.blog.user.application.facade.UserFacade;
import pl.mg.blog.user.core.port.incoming.BlacklistUser;
import pl.mg.blog.user.core.port.incoming.GetUser;
import pl.mg.blog.user.core.port.incoming.RegisterUser;
import pl.mg.blog.user.core.port.incoming.RemoveUserFromBlacklist;
import pl.mg.blog.user.core.port.outgoing.UserDatabase;
import pl.mg.blog.user.core.port.outgoing.UserEventPublisher;
import pl.mg.blog.user.infrastructure.adapter.UserInMemoryRepository;
import pl.mg.blog.user.infrastructure.adapter.UserKafkaEventPublisher;

@Component
public class UserDomainConfig {

    //incoming ports
    @Bean
    @Qualifier("getUser")
    public GetUser getUser(UserDatabase userDatabase, UserEventPublisher eventPublisher) {
        return new UserFacade(userDatabase, eventPublisher);
    }

    @Bean
    @Qualifier("registerUser")
    public RegisterUser registerUser(UserDatabase userDatabase, UserEventPublisher eventPublisher) {
        return new UserFacade(userDatabase, eventPublisher);
    }

    @Bean
    @Qualifier("blacklistUser")
    public BlacklistUser blacklistUser(UserDatabase userDatabase, UserEventPublisher eventPublisher) {
        return new UserFacade(userDatabase, eventPublisher);
    }

    @Bean
    @Qualifier("removeUserFromBlacklist")
    public RemoveUserFromBlacklist removeUserFromBlacklist(UserDatabase userDatabase, UserEventPublisher eventPublisher) {
        return new UserFacade(userDatabase, eventPublisher);
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
