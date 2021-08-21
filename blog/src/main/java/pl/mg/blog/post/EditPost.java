package pl.mg.blog.post;

import lombok.Value;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotNull;

@Value
public class EditPost implements Command {

    @NotNull
    private Long id;

    @NotNull
    private String content;

    @NotNull
    private String title;

}
