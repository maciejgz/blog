package pl.mg.blog.comment.core.model.command;

import lombok.Value;

@Value
public class CheckPostExistenceResponse {

    String postId;
    boolean exists;

}
