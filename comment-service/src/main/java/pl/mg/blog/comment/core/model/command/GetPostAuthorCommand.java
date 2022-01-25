package pl.mg.blog.comment.core.model.command;

import lombok.Value;

@Value
public class GetPostAuthorCommand implements Command {
    String postId;

}
