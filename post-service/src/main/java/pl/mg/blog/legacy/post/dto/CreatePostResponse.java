package pl.mg.blog.legacy.post.dto;

import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class CreatePostResponse {

    @NotEmpty String author;

    @NotEmpty String title;

    @NotEmpty String content;
}
