package pl.mg.blog.comment.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mg.blog.comment.model.AddCommentCommand;

import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

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
