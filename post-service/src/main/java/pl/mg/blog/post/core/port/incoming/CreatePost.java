package pl.mg.blog.post.core.port.incoming;

import pl.mg.blog.post.core.model.command.CreatePostCommand;

public interface CreatePost {

    void createPost(CreatePostCommand command);
}
