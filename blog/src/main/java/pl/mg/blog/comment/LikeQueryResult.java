package pl.mg.blog.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
public class LikeQueryResult {

    @NotNull
    private String username;

    @NotNull
    private Instant date;

    public LikeQueryResult(Like like) {
        this.username = like.getUsername();
        this.date = like.getDate();
    }
}
