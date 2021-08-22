package pl.mg.blog.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document
@AllArgsConstructor
@Data
public class Post {

    @Id
    private String id;

    private String author;

    private String title;

    private String content;

    private Instant created;

    private Instant updatedAt;

    private Long likes;

    private List<String> commentIds;


}
