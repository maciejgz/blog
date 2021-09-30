package pl.mg.blog.post.dto;

import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class CreatePostResponse {

    @NotEmpty String title;

    @NotEmpty String content;
}
