package pl.mg.blog.comment;

import lombok.Data;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddCommentCommand implements Command {

    @NotBlank String username;

    @NotNull String postId;

    @NotBlank String content;
}
