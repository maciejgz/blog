package pl.mg.blog.post;

import lombok.Value;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
public class EditPostCommand implements Command {

    @NotEmpty UUID id;

    @NotEmpty String username;

    @NotNull String content;

    @NotNull String title;

}
