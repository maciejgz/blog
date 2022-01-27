package pl.mg.blog.post.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mg.blog.commons.Query;
import pl.mg.blog.post.core.model.aggregate.Post;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import javax.validation.constraints.NotNull;

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

    private List<String> likes;

    private List<String> comments;

    public static PostQueryResult ofPost(@NotNull Post post) {
        return PostQueryResult.builder()
                .id(post.getId().toString())
                .author(post.getAuthor())
                .title(post.getTitle())
                .content(post.getContent())
                .likes(post.getLikedByUsers())
                .comments(post.getCommentIds())
                .created(post.getCreated()).build();
    }

}
