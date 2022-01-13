package pl.mg.blog.comment.core.port.outgoing;

import pl.mg.blog.comment.core.model.exception.UserNotFoundException;
import pl.mg.blog.comment.core.model.command.CheckBlacklistCommand;
import pl.mg.blog.comment.core.model.command.CheckBlacklistResult;

public interface CheckBlacklist {

    CheckBlacklistResult checkBlacklist(CheckBlacklistCommand command) throws UserNotFoundException;

}
