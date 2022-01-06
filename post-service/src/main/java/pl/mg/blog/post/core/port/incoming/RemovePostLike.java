package pl.mg.blog.post.core.port.incoming;

import pl.mg.blog.post.core.model.command.RemovePostLikeCommand;

public interface RemovePostLike {

    void removePostLikeCommand(RemovePostLikeCommand command);
}
