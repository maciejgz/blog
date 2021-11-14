package pl.mg.blog.comment.model;

import lombok.Value;
import pl.mg.blog.comment.commons.Dto;

import java.time.Instant;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class AddCommentResponse implements Dto {

    @NotBlank String username;

    @NotNull Long postId;

    @NotBlank String content;

    @NotNull Instant created;
}
