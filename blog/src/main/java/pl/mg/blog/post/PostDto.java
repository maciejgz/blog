package pl.mg.blog.post;

import lombok.Data;

import java.time.Instant;

@Data
public class PostDto {

    private long id;

    private String author;

    private String content;

    private Instant created;
}
