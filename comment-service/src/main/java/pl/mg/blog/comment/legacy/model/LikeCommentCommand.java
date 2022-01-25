package pl.mg.blog.comment.legacy.model;

import lombok.Data;
import pl.mg.blog.comment.legacy.commons.Command;

import javax.validation.constraints.NotEmpty;

@Data
public class LikeCommentCommand implements Command {

    @NotEmpty
    String commentId;

    String username;

}
