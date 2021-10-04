package pl.mg.blog.comment.model;

import lombok.Builder;
import lombok.Data;
import pl.mg.blog.comment.commons.QueryResultPage;

import java.util.List;

@Data
@Builder
public class CommentQueryPageResult {

    private List<CommentQueryResult> result;

    private QueryResultPage pageInfo;

}
