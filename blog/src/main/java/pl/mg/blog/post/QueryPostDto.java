package pl.mg.blog.post;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class QueryPostDto {

    private String id;

    private String author;

    private String title;

    private String content;

    private Instant created;

    private Long likes;

    private List<String> comments;

    static QueryPostDto ofPost(Post post) {
        return QueryPostDto.builder()
                .id(post.getId())
                .author(post.getAuthor())
                .title(post.getTitle())
                .content(post.getContent())
                .likes(post.getLikes())
                .comments(post.getCommentIds())
                .created(post.getCreated()).build();
    }
}
