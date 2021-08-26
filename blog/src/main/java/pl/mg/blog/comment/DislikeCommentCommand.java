package pl.mg.blog.comment;

import lombok.Value;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotNull;

@Value
public class DislikeCommentCommand implements Command {

    @NotNull
    String commentId;

    @NotNull
    String username;

}
