package pl.mg.blog.comment;

import lombok.Value;
import pl.mg.blog.commons.Dto;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Value
public class LikeCommentResponse implements Dto {

    @NotNull
    String commentId;

    @NotNull
    String username;

    @NotNull
    Instant date;

}
