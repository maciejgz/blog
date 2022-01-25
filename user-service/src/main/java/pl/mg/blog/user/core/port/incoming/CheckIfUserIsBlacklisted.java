package pl.mg.blog.user.core.port.incoming;

import pl.mg.blog.user.core.model.command.CheckIfUserIsBlacklistedCommand;
import pl.mg.blog.user.core.model.exception.UserNotFoundException;
import pl.mg.blog.user.core.model.response.IsUserBlacklistedResponse;

public interface CheckIfUserIsBlacklisted {

    IsUserBlacklistedResponse checkIfUserIsBlacklisted(CheckIfUserIsBlacklistedCommand command) throws UserNotFoundException;

}
