package pl.mg.blog.user.core;

import pl.mg.blog.user.core.model.User;
import pl.mg.blog.user.core.model.command.BlacklistUserCommand;
import pl.mg.blog.user.core.model.command.RegisterUserCommand;
import pl.mg.blog.user.core.model.command.RemoveUserFromBlacklistCommand;
import pl.mg.blog.user.core.model.event.UserBlacklistedEvent;
import pl.mg.blog.user.core.model.event.UserRegisteredEvent;
import pl.mg.blog.user.core.model.event.UserRemovedFromBlacklistEvent;
import pl.mg.blog.user.core.model.exception.*;
import pl.mg.blog.user.core.port.incoming.BlacklistUser;
import pl.mg.blog.user.core.port.incoming.GetUser;
import pl.mg.blog.user.core.port.incoming.RegisterUser;
import pl.mg.blog.user.core.port.incoming.RemoveUserFromBlacklist;
import pl.mg.blog.user.core.port.outgoing.UserDatabase;
import pl.mg.blog.user.core.port.outgoing.UserEventPublisher;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

/**
 * User facade/service gathering all command handlers. it should not have specific technologies - they should be in
 * the infrastructure part.
 */
public class UserFacade implements BlacklistUser, RegisterUser, RemoveUserFromBlacklist, GetUser {

    private final UserDatabase database;
    private final UserEventPublisher eventPublisher;

    public UserFacade(UserDatabase database, UserEventPublisher eventPublisher) {
        this.database = database;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public User registerUser(RegisterUserCommand command) throws UserRegistrationException {
        if (database.getUser(command.getUsername()).isPresent()) {
            throw new UserRegistrationException(command.getUsername());
        }
        User us = new User(command.getUsername(), command.getPassword(), new ArrayList<>());
        database.save(us);
        //TODO events shall be rather generated in the aggregate than in the domain service
        eventPublisher.publishUserRegisteredEvent(new UserRegisteredEvent(command.getUsername(), command.getPassword()));
        return us;
    }

    @Override
    public void blacklistUser(BlacklistUserCommand command) throws BlacklistUserException, UserAlreadyBlacklistedException, UserNotFoundException {
        Optional<User> user = database.getUser(command.getUser());
        if (user.isPresent()) {
            user.get().blacklistUser(command.getBlacklistedUser());
            database.save(user.get());
            eventPublisher.publishUserBlacklistedEvent(new UserBlacklistedEvent(command.getUser(), command.getBlacklistedUser(), Instant.now()));
        } else {
            throw new UserNotFoundException(command.getUser());
        }
    }

    @Override
    public void removeUserFromBlacklist(RemoveUserFromBlacklistCommand command) throws RemoveUserFromBlacklistException, UserNotBlacklistedException, UserNotFoundException {
        Optional<User> user = database.getUser(command.getUser());
        if (user.isPresent()) {
            user.get().removeUserFromBlacklist(command.getBlacklistedUser());
            database.save(user.get());
            eventPublisher.publishUserRemovedFromBlacklist(new UserRemovedFromBlacklistEvent(command.getUser(), command.getBlacklistedUser(), Instant.now()));
        } else {
            throw new UserNotFoundException(command.getUser());
        }
    }

    @Override
    public User getUser(String username) throws UserNotFoundException {
        return database.getUser(username).orElseThrow(() -> new UserNotFoundException(username));
    }
}
