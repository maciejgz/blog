package pl.mg.blog.post.core.port.incoming;

import pl.mg.blog.post.core.model.aggregate.Post;
import pl.mg.blog.post.core.model.command.EditPostCommand;
import pl.mg.blog.post.core.model.exception.PostNotFoundException;
import pl.mg.blog.post.core.model.exception.UserNotFoundException;

public interface EditPost {

    Post editPost(EditPostCommand command) throws UserNotFoundException, PostNotFoundException;
}
