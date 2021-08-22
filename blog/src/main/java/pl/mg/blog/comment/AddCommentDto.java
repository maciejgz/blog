package pl.mg.blog.comment;

import lombok.Value;
import pl.mg.blog.commons.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class AddCommentDto implements Dto {

    @NotNull Long postId;

    @NotBlank String content;
}
