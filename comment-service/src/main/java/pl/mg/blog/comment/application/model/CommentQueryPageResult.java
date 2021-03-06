package pl.mg.blog.comment.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CommentQueryPageResult {

    private List<CommentQueryResult> result;

    private QueryResultPage pageInfo;

}
