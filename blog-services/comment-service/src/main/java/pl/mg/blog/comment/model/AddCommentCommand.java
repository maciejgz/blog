package pl.mg.blog.comment.model;

import lombok.Data;
import pl.mg.blog.comment.commons.Command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddCommentCommand implements Command {

    String username;

    @NotNull String postId;

    @NotBlank String content;
}
