package pl.mg.blog.post;

import lombok.Value;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotNull;

@Value
public class CreatePost implements Command {

    @NotNull
    private String title;

    @NotNull
    private String content;
}
