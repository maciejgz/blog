package pl.mg.blog.comment;

import lombok.Value;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotNull;

@Value
public class AddComment implements Command {

    @NotNull
    Long postId;

    @NotNull
    String content;
}
