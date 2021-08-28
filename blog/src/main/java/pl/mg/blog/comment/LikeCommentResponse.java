package pl.mg.blog.comment;

import lombok.Value;
import pl.mg.blog.commons.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Value
public class LikeCommentResponse implements Dto {

    @NotBlank
    String commentId;

    @NotBlank
    String username;

    @NotNull
    Instant date;

}
