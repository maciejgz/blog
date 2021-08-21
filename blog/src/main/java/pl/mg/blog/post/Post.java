package pl.mg.blog.post;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public class Post {

    @Id
    private Long id;

    private String author;

    private String content;

    private Instant created;

    private Long likes;


}
