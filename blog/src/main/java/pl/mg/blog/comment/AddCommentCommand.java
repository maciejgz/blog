package pl.mg.blog.comment;

import lombok.Value;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class AddCommentCommand implements Command {

    @NotBlank String username;

    @NotNull Long postId;

    @NotBlank String content;
}
