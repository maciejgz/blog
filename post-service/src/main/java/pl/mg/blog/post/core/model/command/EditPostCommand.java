package pl.mg.blog.post.core.model.command;

import lombok.Value;
import pl.mg.blog.commons.Command;
import pl.mg.blog.legacy.post.validator.PostContentConstraint;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class EditPostCommand implements Command {

    @NotNull String id;

    @NotEmpty String username;

    @NotEmpty String title;

    @NotEmpty
    @PostContentConstraint
    String content;
}
