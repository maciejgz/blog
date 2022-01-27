package pl.mg.blog.comment.core.model.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddCommentCommand implements Command {

    @NotNull String username;

    @NotNull String postId;

    @NotBlank String content;
}
