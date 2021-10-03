package pl.mg.blog.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mg.blog.commons.Query;
import pl.mg.blog.post.repository.Post;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostQueryResult implements Query, Serializable {

    private String id;

    private String author;

    private String title;

    private String content;

    private Instant created;

    private Long likes;

    private List<String> comments;

    public static PostQueryResult ofPost(@NotNull Post post) {
        return PostQueryResult.builder()
                .id(post.getId())
                .author(post.getAuthor())
                .title(post.getTitle())
                .content(post.getContent())
                .likes(post.getLikes())
                .comments(post.getCommentIds())
                .created(post.getCreated()).build();
    }

}
