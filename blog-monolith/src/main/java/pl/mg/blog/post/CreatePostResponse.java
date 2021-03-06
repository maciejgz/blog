package pl.mg.blog.post;

import lombok.Value;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotEmpty;

@Value
public class CreatePostResponse {

    @NotEmpty String title;

    @NotEmpty String content;
}
