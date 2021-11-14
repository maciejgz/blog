package pl.mg.blog.post.dto;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class EditPostResponse {

    @NotNull String author;

    @NotNull String id;

    @NotEmpty String content;

    @NotEmpty String title;

}
