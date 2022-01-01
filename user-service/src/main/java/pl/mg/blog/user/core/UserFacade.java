package pl.mg.blog.user.core;

import org.springframework.stereotype.Service;
import pl.mg.blog.user.core.model.User;
import pl.mg.blog.user.core.model.command.BlacklistUserCommand;
import pl.mg.blog.user.core.model.command.RegisterUserCommand;
import pl.mg.blog.user.core.model.command.RemoveUserFromBlacklistCommand;
import pl.mg.blog.user.core.model.exception.BlacklistUserException;
import pl.mg.blog.user.core.model.exception.RemoveUserFromBlacklistException;
import pl.mg.blog.user.core.model.exception.UserRegistrationException;
import pl.mg.blog.user.core.port.incoming.BlacklistUser;
import pl.mg.blog.user.core.port.incoming.RegisterUser;
import pl.mg.blog.user.core.port.incoming.RemoveUserFromBlacklist;
import pl.mg.blog.user.core.port.outgoing.UserDatabase;
import pl.mg.blog.user.core.port.outgoing.UserEventPublisher;

import java.util.ArrayList;

/**
 * User facade/service gathering all command handlers.
 */
@Service
public class UserFacade implements BlacklistUser, RegisterUser, RemoveUserFromBlacklist {

    private final UserDatabase database;
    private final UserEventPublisher eventPublisher;

    public UserFacade(UserDatabase database, UserEventPublisher eventPublisher) {
        this.database = database;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public String registerUser(RegisterUserCommand command) throws UserRegistrationException {
        if (database.getUser(command.getUsername()).isPresent()) {
            throw new UserRegistrationException(command.getUsername());
        }
        User us = new User(command.getUsername(), command.getPassword(), new ArrayList<>());
        database.save(us);
        return command.getUsername();
    }

    @Override
    public void blacklistUser(BlacklistUserCommand command) throws BlacklistUserException {
        //TODO implement
    }

    @Override
    public void removeUserFromBlacklist(RemoveUserFromBlacklistCommand command) throws RemoveUserFromBlacklistException {
        //TODO implement
    }
}
