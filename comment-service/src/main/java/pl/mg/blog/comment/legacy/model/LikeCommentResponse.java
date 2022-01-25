package pl.mg.blog.comment.legacy.model;

import lombok.Value;
import pl.mg.blog.comment.legacy.commons.Dto;

import java.time.Instant;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class LikeCommentResponse implements Dto {

    @NotBlank
    String commentId;

    @NotBlank
    String username;

    @NotNull
    Instant date;

}
