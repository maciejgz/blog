package pl.mg.blog.post.core.port.incoming;

import pl.mg.blog.post.core.model.command.LikePostCommand;

public interface LikePost {

    void likePost(LikePostCommand command);

}
