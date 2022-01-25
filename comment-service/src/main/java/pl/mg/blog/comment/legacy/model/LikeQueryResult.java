package pl.mg.blog.comment.legacy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mg.blog.comment.legacy.commons.Dto;
import pl.mg.blog.comment.legacy.repository.Like;

import java.io.Serializable;
import java.time.Instant;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeQueryResult implements Dto, Serializable {

    @NotNull
    private String username;

    @NotNull
    private Instant date;

    public LikeQueryResult(Like like) {
        this.username = like.getUsername();
        this.date = like.getDate();
    }
}
