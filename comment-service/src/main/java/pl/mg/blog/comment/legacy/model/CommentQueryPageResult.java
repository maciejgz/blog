package pl.mg.blog.comment.legacy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pl.mg.blog.comment.legacy.commons.QueryResultPage;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CommentQueryPageResult {

    private List<CommentQueryResult> result;

    private QueryResultPage pageInfo;

}
