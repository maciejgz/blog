package pl.mg.blog.comment.model;

import lombok.Data;
import pl.mg.blog.comment.commons.QueryPageableCommand;

@Data
public class GetCommentsByUserIdCommand {

    private String username;

    private QueryPageableCommand pageableCommand;
}
