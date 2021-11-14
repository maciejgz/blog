package pl.mg.blog.simulator.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
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


}
