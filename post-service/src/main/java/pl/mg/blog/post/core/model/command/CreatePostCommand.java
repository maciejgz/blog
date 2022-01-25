package pl.mg.blog.post.core.model.command;

import lombok.Value;
import pl.mg.blog.commons.Command;
import pl.mg.blog.post.application.validator.PostContentConstraint;

import javax.validation.constraints.NotEmpty;

@Value
public class CreatePostCommand implements Command {

    @NotEmpty String username;

    @NotEmpty String title;

    @NotEmpty
    @PostContentConstraint
    String content;

}
