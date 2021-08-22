package pl.mg.blog.post;

import lombok.Value;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class EditPostDto implements Command {

    @NotNull String id;

    @NotEmpty String content;

    @NotEmpty String title;

}
