package pl.mg.blog.comment;

import lombok.Value;
import pl.mg.blog.commons.Dto;

import javax.validation.constraints.NotNull;

@Value
public class LikeCommentDto implements Dto {

    @NotNull
    Long commentId;

}
