package pl.mg.blog.user.application.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.mg.blog.user.core.model.User;
import pl.mg.blog.user.core.model.command.BlacklistUserCommand;
import pl.mg.blog.user.core.model.command.CheckIfUserIsBlacklistedCommand;
import pl.mg.blog.user.core.model.command.RegisterUserCommand;
import pl.mg.blog.user.core.model.command.RemoveUserFromBlacklistCommand;
import pl.mg.blog.user.core.model.exception.*;
import pl.mg.blog.user.core.model.response.IsUserBlacklistedResponse;
import pl.mg.blog.user.core.port.incoming.*;

/**
 * User application service (facade) gathering all command handlers and many domain services.
 * It can contain specific technologies like transactions because it is not a part of the domain.
 * There should be DTO to domain model translation.
 */
//TODO transaction should be here to not put any tech specific annotations
@Service
public class UserFacade {

    private final GetUser getUserAdapter;
    private final RegisterUser registerUserAdapter;
    private final BlacklistUser blacklistUserAdapter;
    private final RemoveUserFromBlacklist removeUserFromBlacklistAdapter;
    private final CheckIfUserIsBlacklisted checkIfUserIsBlacklisted;

    public UserFacade(@Qualifier("getUser") GetUser getUserAdapter,
                      @Qualifier("registerUser") RegisterUser registerUserAdapter,
                      @Qualifier("blacklistUser") BlacklistUser blacklistUserAdapter,
                      @Qualifier("removeUserFromBlacklist") RemoveUserFromBlacklist removeUserFromBlacklistAdapter,
                      @Qualifier("checkIfUserIsBlacklisted") CheckIfUserIsBlacklisted checkIfUserIsBlacklisted) {
        this.getUserAdapter = getUserAdapter;
        this.registerUserAdapter = registerUserAdapter;
        this.blacklistUserAdapter = blacklistUserAdapter;
        this.removeUserFromBlacklistAdapter = removeUserFromBlacklistAdapter;
        this.checkIfUserIsBlacklisted = checkIfUserIsBlacklisted;
    }

    public User registerUser(RegisterUserCommand command) throws UserRegistrationException {

        //TODO argument should be controller DTO instead of domain object
        return registerUserAdapter.registerUser(command);
    }

    public void blacklistUser(BlacklistUserCommand command) throws BlacklistUserException, UserAlreadyBlacklistedException, UserNotFoundException {
        //TODO argument should be controller DTO instead of domain object
        this.blacklistUserAdapter.blacklistUser(command);
    }

    public void removeUserFromBlacklist(RemoveUserFromBlacklistCommand command) throws RemoveUserFromBlacklistException, UserNotBlacklistedException, UserNotFoundException {
        //TODO argument should be controller DTO instead of domain object
        this.removeUserFromBlacklistAdapter.removeUserFromBlacklist(command);
    }

    public User getUser(String username) throws UserNotFoundException {
        //TODO argument should be controller DTO instead of domain object
        return this.getUserAdapter.getUser(username);
    }

    public IsUserBlacklistedResponse checkIfUserIsBlacklisted(String username, String blacklistedUser) throws UserNotFoundException {
        return this.checkIfUserIsBlacklisted.checkIfUserIsBlacklisted(new CheckIfUserIsBlacklistedCommand(username, blacklistedUser));
    }
}
