package pl.mg.blog.post.core.port.incoming;

import pl.mg.blog.post.core.model.aggregate.Post;
import pl.mg.blog.post.core.model.command.CreatePostCommand;
import pl.mg.blog.post.core.model.exception.UserNotFoundException;

public interface CreatePost {

    Post createPost(CreatePostCommand command) throws UserNotFoundException;

}
