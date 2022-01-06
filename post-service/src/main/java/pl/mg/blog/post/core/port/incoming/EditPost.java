package pl.mg.blog.post.core.port.incoming;

import pl.mg.blog.post.core.model.command.EditPostCommand;

public interface EditPost {

    void editPost(EditPostCommand command);
}
