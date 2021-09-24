package pl.mg.blog.simulator.commons;

import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class CreatePostCommand implements Command {

    @NotEmpty String username;

    @NotEmpty String title;

    @NotEmpty String content;

}
