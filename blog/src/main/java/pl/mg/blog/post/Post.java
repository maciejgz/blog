package pl.mg.blog.post;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document
@AllArgsConstructor
public class Post {

    @Id
    private UUID id;

    private String author;

    private String title;

    private String content;

    private Instant created;

    private Long likes;


}
