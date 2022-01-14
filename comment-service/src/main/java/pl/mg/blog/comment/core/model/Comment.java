package pl.mg.blog.comment.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mg.blog.comment.core.model.command.AddCommentCommand;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Comment aggregate.
 */
@AllArgsConstructor
@Getter
public class Comment {

    private UUID commentId;
    private String content;
    private String author;
    private UUID postId;
    private List<String> likes;
    private Instant createdAt;

    public static Comment ofCommand(AddCommentCommand command) {
        return new Comment(
                UUID.randomUUID(),
                command.getContent(),
                command.getUsername(),
                UUID.fromString(command.getPostId()),
                new ArrayList<>(),
                Instant.now()
        );
    }

}
