package pl.mg.blog.comment.core.model.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostQueryResult implements Serializable {

    private String id;

    private String author;

    private String title;

    private String content;

    private Instant created;

    private Long likes;

    private List<String> comments;

}
