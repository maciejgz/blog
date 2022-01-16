package pl.mg.blog.comment.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mg.blog.comment.core.model.Comment;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentQueryResult implements Serializable {

    @NotNull
    private String id;

    private String content;

    @NotNull
    private String author;

    @NotNull
    private String postId;

    private Instant created;

    private List<LikeQueryResult> likes;

    public static CommentQueryResult ofComment(Comment comment) {
        CommentQueryResult result = new CommentQueryResult();
        result.id = comment.getCommentId().toString();
        result.content = comment.getContent();
        result.author = comment.getAuthor();
        result.postId = comment.getPostId().toString();
        result.created = comment.getCreatedAt();
        if (comment.getLikes() != null) {
            result.likes = comment.getLikes().stream().map(LikeQueryResult::new).collect(Collectors.toList());
        }
        return result;
    }

}
