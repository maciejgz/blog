package pl.mg.blog.legacy.post.dto;

import lombok.Value;
import pl.mg.blog.commons.Command;
import pl.mg.blog.post.application.validator.PostContentConstraint;

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
