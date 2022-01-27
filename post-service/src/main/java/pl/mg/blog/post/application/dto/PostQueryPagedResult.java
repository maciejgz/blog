package pl.mg.blog.post.application.dto;

import lombok.Data;
import pl.mg.blog.commons.QueryResultPage;

import java.util.List;

@Data
public class PostQueryPagedResult {

    List<PostQueryResult> results;

    QueryResultPage page;

}
