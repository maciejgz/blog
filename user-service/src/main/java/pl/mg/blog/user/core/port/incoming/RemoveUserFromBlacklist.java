package pl.mg.blog.user.core.port.incoming;

import pl.mg.blog.user.core.model.command.RemoveUserFromBlacklistCommand;
import pl.mg.blog.user.core.model.exception.RemoveUserFromBlacklistException;

/**
 * Incoming port of removing previously added user from blacklist
 */
public interface RemoveUserFromBlacklist {

    void removeUserFromBlacklist(RemoveUserFromBlacklistCommand command) throws RemoveUserFromBlacklistException;
}
