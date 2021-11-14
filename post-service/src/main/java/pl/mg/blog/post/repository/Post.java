package pl.mg.blog.post.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Data
public class Post {

    private String id;

    private String author;

    private String title;

    private String content;

    private Instant created;

    private Instant updatedAt;

    private Long likes;

    private List<String> commentIds;

}
