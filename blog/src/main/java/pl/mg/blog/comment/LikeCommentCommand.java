package pl.mg.blog.comment;

import lombok.Value;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
public class LikeCommentCommand implements Command {

    @NotNull
    String commentId;

}
