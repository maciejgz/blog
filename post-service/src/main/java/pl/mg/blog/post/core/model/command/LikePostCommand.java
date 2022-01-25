package pl.mg.blog.post.core.model.command;

import lombok.Value;
import pl.mg.blog.commons.Command;

@Value
public class LikePostCommand implements Command {

    String postId;

    String username;

}
