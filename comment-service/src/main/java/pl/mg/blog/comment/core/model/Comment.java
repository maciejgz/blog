package pl.mg.blog.comment.core.model;

import pl.mg.blog.comment.core.model.command.AddCommentCommand;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Comment aggregate.
 */
public class Comment {

    private UUID commentId;
    private String content;
    private String author;
    private UUID postId;
    private List<String> likes;
    private Instant createdAt;


    public static Comment ofCommand(AddCommentCommand command) {
        //TODO implement
        return null;
    }

}
