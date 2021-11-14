package pl.mg.blog.comment;

import lombok.Data;
import pl.mg.blog.commons.QueryPageableCommand;

@Data
public class GetCommentsByUserIdCommand {

    private String username;

    private QueryPageableCommand pageableCommand;
}
