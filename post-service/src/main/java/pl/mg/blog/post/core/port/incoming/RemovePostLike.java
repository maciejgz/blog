package pl.mg.blog.post.core.port.incoming;

import pl.mg.blog.post.core.model.command.RemovePostLikeCommand;
import pl.mg.blog.post.core.model.exception.PostAlreadyNotLikedException;
import pl.mg.blog.post.core.model.exception.PostNotFoundException;
import pl.mg.blog.post.core.model.exception.UserBlacklistedException;
import pl.mg.blog.post.core.model.exception.UserNotFoundException;

public interface RemovePostLike {

    void removePostLikeCommand(RemovePostLikeCommand command) throws UserNotFoundException, PostNotFoundException, UserBlacklistedException, PostAlreadyNotLikedException;
}
