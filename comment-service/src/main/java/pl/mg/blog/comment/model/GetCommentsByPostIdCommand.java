package pl.mg.blog.comment.model;

import lombok.Data;
import pl.mg.blog.comment.commons.QueryPageableCommand;

@Data
public class GetCommentsByPostIdCommand {

    private String postId;

    private QueryPageableCommand pageableCommand;
}
