package pl.mg.blog.post;

import lombok.Value;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Value
public class EditPostDto implements Command {

    @NotEmpty UUID id;

    @NotEmpty String content;

    @NotEmpty String title;

}
