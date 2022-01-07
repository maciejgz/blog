package pl.mg.blog.user.application.service;

import pl.mg.blog.user.core.model.User;
import pl.mg.blog.user.core.model.command.BlacklistUserCommand;
import pl.mg.blog.user.core.model.command.RegisterUserCommand;
import pl.mg.blog.user.core.model.command.RemoveUserFromBlacklistCommand;
import pl.mg.blog.user.core.model.exception.*;
import pl.mg.blog.user.core.port.incoming.BlacklistUser;
import pl.mg.blog.user.core.port.incoming.GetUser;
import pl.mg.blog.user.core.port.incoming.RegisterUser;
import pl.mg.blog.user.core.port.incoming.RemoveUserFromBlacklist;

/**
 * User application service (facade) gathering all command handlers and many domain services.
 * It can contain specific technologies like transactions because it is not a part of the domain.
 * There should be DTO to domain model translation.
 */
//TODO transaction should be here to not put any tech specific annotations
public class UserFacade {

    private final GetUser getUserAdapter;
    private final RegisterUser registerUserAdapter;
    private final BlacklistUser blacklistUserAdapter;
    private final RemoveUserFromBlacklist removeUserFromBlacklistAdapter;

    public UserFacade(GetUser getUserAdapter, RegisterUser registerUserAdapter, BlacklistUser blacklistUserAdapter, RemoveUserFromBlacklist removeUserFromBlacklistAdapter) {
        this.getUserAdapter = getUserAdapter;
        this.registerUserAdapter = registerUserAdapter;
        this.blacklistUserAdapter = blacklistUserAdapter;
        this.removeUserFromBlacklistAdapter = removeUserFromBlacklistAdapter;
    }

    public User registerUser(RegisterUserCommand command) throws UserRegistrationException {
        return registerUserAdapter.registerUser(command);
    }

    public void blacklistUser(BlacklistUserCommand command) throws BlacklistUserException, UserAlreadyBlacklistedException, UserNotFoundException {
        this.blacklistUserAdapter.blacklistUser(command);
    }

    public void removeUserFromBlacklist(RemoveUserFromBlacklistCommand command) throws RemoveUserFromBlacklistException, UserNotBlacklistedException, UserNotFoundException {
        this.removeUserFromBlacklistAdapter.removeUserFromBlacklist(command);
    }

    public User getUser(String username) throws UserNotFoundException {
        return this.getUserAdapter.getUser(username);
    }
}
