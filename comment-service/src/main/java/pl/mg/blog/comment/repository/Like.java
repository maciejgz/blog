package pl.mg.blog.comment.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Like {

    @NotNull
    private String username;

    @NotNull
    private Instant date;

}
