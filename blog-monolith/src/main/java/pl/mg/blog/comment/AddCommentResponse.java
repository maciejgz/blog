package pl.mg.blog.comment;

import lombok.Value;
import pl.mg.blog.commons.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Value
public class AddCommentResponse implements Dto {

    @NotBlank String username;

    @NotNull Long postId;

    @NotBlank String content;

    @NotNull Instant created;
}
