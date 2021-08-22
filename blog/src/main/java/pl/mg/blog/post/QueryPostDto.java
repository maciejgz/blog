package pl.mg.blog.post;

import lombok.Data;

import java.time.Instant;

@Data
public class QueryPostDto {

    private long id;

    private String author;

    private String title;

    private String content;

    private Instant created;

    private Long likes;
}
