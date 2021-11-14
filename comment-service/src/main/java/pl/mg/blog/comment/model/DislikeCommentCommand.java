package pl.mg.blog.comment.model;

import lombok.Data;
import pl.mg.blog.comment.commons.Command;

import javax.validation.constraints.NotEmpty;

@Data
public class DislikeCommentCommand implements Command {

    @NotEmpty
    String commentId;

    String username;

}
