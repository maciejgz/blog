package pl.mg.blog.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
