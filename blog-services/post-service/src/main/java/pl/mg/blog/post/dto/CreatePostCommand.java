package pl.mg.blog.post.dto;

import pl.mg.blog.commons.Command;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class CreatePostCommand implements Command {

    @NotEmpty String username;

    @NotEmpty String title;

    @NotEmpty String content;

}
