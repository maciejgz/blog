package pl.mg.blog.comment.model;

import lombok.Value;
import pl.mg.blog.comment.commons.Dto;

import javax.validation.constraints.NotNull;

@Value
public class DislikeCommentResponse implements Dto {

    @NotNull
    String commentId;

    @NotNull
    String username;

}
