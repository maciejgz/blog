package pl.mg.blog.simulator.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
