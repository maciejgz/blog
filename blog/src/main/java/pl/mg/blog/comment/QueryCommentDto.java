package pl.mg.blog.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@AllArgsConstructor
public class QueryCommentDto {

    @NotNull
    private long id;

    private String content;

    @NotNull
    private String author;

    @NotNull
    private long postId;

    private Instant created;

    private long likes;

}
