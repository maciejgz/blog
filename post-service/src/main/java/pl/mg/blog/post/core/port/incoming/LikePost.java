package pl.mg.blog.post.core.port.incoming;

import pl.mg.blog.post.core.model.command.LikePostCommand;
import pl.mg.blog.post.core.model.exception.PostAlreadyLikedException;
import pl.mg.blog.post.core.model.exception.PostNotFoundException;
import pl.mg.blog.post.core.model.exception.UserBlacklistedException;
import pl.mg.blog.post.core.model.exception.UserNotFoundException;

public interface LikePost {

    void likePost(LikePostCommand command) throws UserNotFoundException, PostNotFoundException, UserBlacklistedException, PostAlreadyLikedException;

}
