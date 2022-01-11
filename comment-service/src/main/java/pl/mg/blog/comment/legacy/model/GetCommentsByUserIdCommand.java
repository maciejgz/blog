package pl.mg.blog.comment.legacy.model;

import lombok.Data;
import pl.mg.blog.comment.legacy.commons.QueryPageableCommand;

@Data
public class GetCommentsByUserIdCommand {

    private String username;

    private QueryPageableCommand pageableCommand;
}
