package pl.mg.blog.comment.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mg.blog.comment.core.model.command.AddCommentCommand;
import pl.mg.blog.comment.core.model.command.DislikeCommentCommand;
import pl.mg.blog.comment.core.model.command.LikeCommentCommand;
import pl.mg.blog.comment.core.model.exception.CommentAlreadyDislikedException;
import pl.mg.blog.comment.core.model.exception.CommentAlreadyLikedException;

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

    public void likeComment(LikeCommentCommand command) throws CommentAlreadyLikedException {
        if (likes.contains(command.getUsername())) {
            throw new CommentAlreadyLikedException("Comment " + commentId.toString() + " is already liked by " + command.getUsername());
        }
        likes.add(command.getUsername());
    }

    public void dislikeComment(DislikeCommentCommand command) throws CommentAlreadyDislikedException {
        if (!likes.contains(command.getUsername())) {
            throw new CommentAlreadyDislikedException("Comment " + commentId.toString() + " is already disliked by " + command.getUsername());
        }
        likes.remove(command.getUsername());
    }

}
