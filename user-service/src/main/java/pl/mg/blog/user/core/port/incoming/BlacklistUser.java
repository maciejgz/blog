package pl.mg.blog.user.core.port.incoming;

import pl.mg.blog.user.core.model.command.BlacklistUserCommand;
import pl.mg.blog.user.core.model.exception.BlacklistUserException;
import pl.mg.blog.user.core.model.exception.UserAlreadyBlacklistedException;
import pl.mg.blog.user.core.model.exception.UserNotFoundException;

/**
 * Incoming port for blacklisting user.
 */
public interface BlacklistUser {

    void blacklistUser(BlacklistUserCommand command) throws BlacklistUserException, UserAlreadyBlacklistedException, UserNotFoundException;

}
