package pl.mg.blog.simulator.commons;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LikeCommentCommand implements Command {

    @NotEmpty
    String commentId;

    String username;

}
