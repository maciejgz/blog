package pl.mg.blog.simulator.commons;

import lombok.Value;

@Value
public class LikePostCommand implements Command {

    String postId;

    String username;

}
