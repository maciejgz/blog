package pl.mg.blog.comment.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pl.mg.blog.comment.legacy.commons.QueryResultPage;
import pl.mg.blog.comment.legacy.model.CommentQueryResult;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CommentQueryPageResult {

    private List<CommentQueryResult> result;

    private QueryResultPage pageInfo;

}
