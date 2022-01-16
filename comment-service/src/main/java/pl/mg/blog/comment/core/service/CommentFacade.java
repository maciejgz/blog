package pl.mg.blog.comment.core.service;

import pl.mg.blog.comment.core.model.Comment;
import pl.mg.blog.comment.core.model.command.*;
import pl.mg.blog.comment.core.model.event.CommentAddedEvent;
import pl.mg.blog.comment.core.model.event.CommentDislikedEvent;
import pl.mg.blog.comment.core.model.event.CommentLikedEvent;
import pl.mg.blog.comment.core.model.exception.*;
import pl.mg.blog.comment.core.port.incoming.AddComment;
import pl.mg.blog.comment.core.port.incoming.DislikeComment;
import pl.mg.blog.comment.core.port.incoming.GetComment;
import pl.mg.blog.comment.core.port.incoming.LikeComment;
import pl.mg.blog.comment.core.port.outgoing.*;

import java.util.Optional;

/**
 * Facade of all incoming comment ports
 */
public class CommentFacade implements AddComment, DislikeComment, LikeComment, GetComment {

    private final CheckBlacklist checkBlacklist;
    private final CheckPostExistence checkPostExistence;
    private final CheckUserExistence checkUserExistence;
    private final GetPostAuthor getPostAuthor;
    private final CommentDatabase commentDatabase;
    private final CommentEventPublisher commentEventPublisher;

    public CommentFacade(CheckBlacklist checkBlacklist, CheckPostExistence checkPostExistence, CheckUserExistence checkUserExistence, GetPostAuthor getPostAuthor, CommentDatabase commentDatabase, CommentEventPublisher commentEventPublisher) {
        this.checkBlacklist = checkBlacklist;
        this.checkPostExistence = checkPostExistence;
        this.checkUserExistence = checkUserExistence;
        this.getPostAuthor = getPostAuthor;
        this.commentDatabase = commentDatabase;
        this.commentEventPublisher = commentEventPublisher;
    }

    @Override
    public Comment addComment(AddCommentCommand command) throws UserNotFoundException, PostNotFoundException, UserBlacklistedException {
        if (!checkUserExistence.checkUserExistence(new CheckUserExistenceCommand(command.getUsername())).isUserExists()) {
            throw new UserNotFoundException("User not found " + command.getUsername());
        }
        if (!checkPostExistence.checkPostExistence(new CheckPostExistenceCommand(command.getPostId())).isExists()) {
            throw new PostNotFoundException("Post not found " + command.getPostId());
        }
        PostAuthorResponse postAuthorUsername = getPostAuthor.getPostAuthor(new GetPostAuthorCommand(command.getPostId()));
        if (checkBlacklist.checkBlacklist(new CheckBlacklistCommand(postAuthorUsername.getPostAuthor(), command.getUsername())).isBlacklisted()) {
            throw new UserBlacklistedException("User " + command.getUsername() + " is blacklisted by " + postAuthorUsername.getPostAuthor());
        }
        Comment comment = Comment.ofCommand(command);
        commentDatabase.save(comment);
        commentEventPublisher.publishCommentAddedEvent(new CommentAddedEvent(command.getPostId(), command.getUsername(), command.getContent()));
        return comment;
    }

    @Override
    public void likeComment(LikeCommentCommand command) throws CommentNotExistException, UserBlacklistedException, CommentAlreadyLikedException, UserNotFoundException {
        if (!checkUserExistence.checkUserExistence(new CheckUserExistenceCommand(command.getUsername())).isUserExists()) {
            throw new UserNotFoundException("User not found " + command.getUsername());
        }
        Optional<Comment> commentOpt = commentDatabase.getComment(command.getCommentId());
        if (commentOpt.isEmpty()) {
            throw new CommentNotExistException("Comment " + command.getCommentId() + " does not exist");
        }
        Comment comment = commentOpt.get();
        if (checkBlacklist.checkBlacklist(new CheckBlacklistCommand(comment.getAuthor(), command.getUsername())).isBlacklisted()) {
            throw new UserBlacklistedException("User " + command.getUsername() + " is blacklisted by " + comment.getAuthor());
        }
        comment.likeComment(command);
        commentDatabase.save(comment);
        commentEventPublisher.publishCommentLikedEvent(new CommentLikedEvent(comment.getPostId().toString(),
                comment.getCommentId().toString(), command.getUsername()));
    }

    @Override
    public void dislikeComment(DislikeCommentCommand command) throws UserNotFoundException, CommentNotExistException, UserBlacklistedException, CommentAlreadyDislikedException {
        if (!checkUserExistence.checkUserExistence(new CheckUserExistenceCommand(command.getUsername())).isUserExists()) {
            throw new UserNotFoundException("User not found " + command.getUsername());
        }
        Optional<Comment> commentOpt = commentDatabase.getComment(command.getCommentId());
        if (commentOpt.isEmpty()) {
            throw new CommentNotExistException("Comment " + command.getCommentId() + " does not exist");
        }
        Comment comment = commentOpt.get();
        if (checkBlacklist.checkBlacklist(new CheckBlacklistCommand(comment.getAuthor(), command.getUsername())).isBlacklisted()) {
            throw new UserBlacklistedException("User " + command.getUsername() + " is blacklisted by " + comment.getAuthor());
        }
        comment.dislikeComment(command);
        commentDatabase.save(comment);
        commentEventPublisher.publishCommentDislikedEvent(new CommentDislikedEvent(comment.getPostId().toString(),
                comment.getCommentId().toString(), command.getUsername()));
    }

    @Override
    public Comment getComment(String commentId) throws CommentNotExistException {
        Optional<Comment> commentOpt = commentDatabase.getComment(commentId);
        if (commentOpt.isEmpty()) {
            throw new CommentNotExistException("Comment " + commentId + " does not exist");
        }
        return commentOpt.get();
    }
}
