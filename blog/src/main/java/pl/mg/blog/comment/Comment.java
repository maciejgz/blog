package pl.mg.blog.comment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public class Comment {

    @Id
    private long id;

    private String content;

    private String author;

    private long postId;

    private Instant created;

    private long likes;

}