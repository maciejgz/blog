package pl.mg.blog.comment;

import lombok.Value;
import pl.mg.blog.commons.Dto;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
public class LikeCommentDto implements Dto {

    @NotNull
    String commentId;

}
