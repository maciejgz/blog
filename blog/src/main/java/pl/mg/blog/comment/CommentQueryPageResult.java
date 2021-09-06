package pl.mg.blog.comment;

import lombok.Builder;
import lombok.Data;
import pl.mg.blog.commons.QueryResultPage;

import java.util.List;

@Data
@Builder
public class CommentQueryPageResult {

    private List<CommentQueryResult> result;

    private QueryResultPage pageInfo;

}
