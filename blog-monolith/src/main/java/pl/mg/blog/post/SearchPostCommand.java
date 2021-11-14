package pl.mg.blog.post;

import lombok.Value;
import pl.mg.blog.commons.QueryPageableCommand;

@Value
public class SearchPostCommand {

    String query;

    QueryPageableCommand pageableCommand;

}
