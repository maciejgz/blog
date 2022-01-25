package pl.mg.blog.comment.legacy.model;

import lombok.Data;
import pl.mg.blog.comment.legacy.commons.QueryPageableCommand;

@Data
public class GetCommentsByPostIdCommand {

    private String postId;

    private QueryPageableCommand pageableCommand;
}
