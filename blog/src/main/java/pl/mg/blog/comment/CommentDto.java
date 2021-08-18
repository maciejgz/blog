package pl.mg.blog.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@AllArgsConstructor
public class CommentDto {

    @NotNull
    private long id;

    private String content;

    private String author;

    private long postId;

    private Instant created;

    private long likes;

}
