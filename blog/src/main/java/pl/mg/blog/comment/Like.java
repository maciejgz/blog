package pl.mg.blog.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@AllArgsConstructor
public class Like {

    @NotNull
    private String username;

    @NotNull
    private Instant date;

}
