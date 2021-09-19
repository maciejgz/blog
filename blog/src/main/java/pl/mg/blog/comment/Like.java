package pl.mg.blog.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@AllArgsConstructor
public class Like {

    @NotNull
    private String username;

    @NotNull
    @Field(type = FieldType.Date)
    private Instant date;

}
