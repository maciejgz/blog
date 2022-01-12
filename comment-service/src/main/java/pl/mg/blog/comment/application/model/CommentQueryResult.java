package pl.mg.blog.comment.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mg.blog.comment.legacy.commons.Query;
import pl.mg.blog.comment.legacy.model.LikeQueryResult;
import pl.mg.blog.comment.legacy.repository.Comment;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentQueryResult implements Query, Serializable {

    @NotNull
    private String id;

    private String content;

    @NotNull
    private String author;

    @NotNull
    private String postId;

    private Instant created;

    private List<LikeQueryResult> likes;

    public CommentQueryResult(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.author = comment.getAuthor();
        this.postId = comment.getPostId();
        this.created = comment.getCreated();
        if (comment.getLikes() != null) {
            this.likes = comment.getLikes().stream().map(LikeQueryResult::new).collect(Collectors.toList());
        }
    }

}
