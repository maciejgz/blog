package pl.mg.blog.comment;

import lombok.Data;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotEmpty;

@Data
public class DislikeCommentCommand implements Command {

    @NotEmpty
    String commentId;

    String username;

}
