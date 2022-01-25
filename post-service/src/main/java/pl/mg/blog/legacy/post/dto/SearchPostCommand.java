package pl.mg.blog.legacy.post.dto;

import lombok.Value;

@Value
public class SearchPostCommand {

    String query;

    QueryPageableCommand pageableCommand;

}
