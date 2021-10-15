package pl.mg.blog.comment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mg.blog.comment.commons.Query;
import pl.mg.blog.comment.repository.Comment;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

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
