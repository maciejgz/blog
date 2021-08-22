package pl.mg.blog.post;

import lombok.Value;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotEmpty;

@Value
public class CreatePostCommand implements Command {

    @NotEmpty String username;

    @NotEmpty String title;

    @NotEmpty String content;

}
