package pl.mg.blog.post.core.port.incoming;

import pl.mg.blog.post.core.model.aggregate.Post;
import pl.mg.blog.post.core.model.command.GetPostCommand;
import pl.mg.blog.post.core.model.exception.PostNotFoundException;

public interface GetPost {

    Post getPost(GetPostCommand command) throws PostNotFoundException;

}
