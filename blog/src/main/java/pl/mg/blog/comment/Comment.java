package pl.mg.blog.comment;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@Document
@Data
public class Comment {

    @Id
    private String id;

    private String content;

    private String author;

    private String postId;

    private Instant created;

    private Set<Like> likes;

    public Comment(AddCommentCommand command) {
        this.author = command.getUsername();
        this.content = command.getContent();
        this.postId = command.getPostId();
    }

}
