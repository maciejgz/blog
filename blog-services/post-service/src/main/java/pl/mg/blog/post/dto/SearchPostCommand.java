package pl.mg.blog.post.dto;

import lombok.Value;

@Value
public class SearchPostCommand {

    String query;

    QueryPageableCommand pageableCommand;

}
