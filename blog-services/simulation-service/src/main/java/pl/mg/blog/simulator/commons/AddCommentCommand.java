package pl.mg.blog.simulator.commons;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddCommentCommand implements Command {

    String username;

    @NotNull String postId;

    @NotBlank String content;
}
