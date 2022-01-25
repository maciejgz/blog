package pl.mg.blog.comment.core.model.command;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class LikeCommentCommand implements Command {

    @NotEmpty
    String commentId;

    String username;

}
