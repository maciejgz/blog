package pl.mg.blog.comment;

import lombok.Data;
import pl.mg.blog.commons.QueryPageableCommand;

@Data
public class GetCommentsByPostIdCommand {

    private String postId;

    private QueryPageableCommand pageableCommand;
}
