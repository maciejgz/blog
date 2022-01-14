package pl.mg.blog.comment.core.service;

import pl.mg.blog.comment.core.model.Comment;
import pl.mg.blog.comment.core.model.command.*;
import pl.mg.blog.comment.core.model.event.CommentAddedEvent;
import pl.mg.blog.comment.core.model.exception.PostNotFoundException;
import pl.mg.blog.comment.core.model.exception.UserBlacklistedException;
import pl.mg.blog.comment.core.model.exception.UserNotFoundException;
import pl.mg.blog.comment.core.port.incoming.AddComment;
import pl.mg.blog.comment.core.port.incoming.DislikeComment;
import pl.mg.blog.comment.core.port.incoming.LikeComment;
import pl.mg.blog.comment.core.port.outgoing.*;

/**
 * Facade of all incoming comment ports
 */
public class CommentFacade implements AddComment, DislikeComment, LikeComment {

    private final CheckBlacklist checkBlacklist;
    private final CheckPostExistence checkPostExistence;
    private final CheckUserExistence checkUserExistence;
    private final GetPostAuthor getPostAuthor;
    private final CommentDatabase commentDatabase;
    private final CommentEventPublisher commentEventPublisher;

    public CommentFacade(CheckBlacklist checkBlacklist, CheckPostExistence checkPostExistence,
                         CheckUserExistence checkUserExistence, GetPostAuthor getPostAuthor, CommentDatabase commentDatabase, CommentEventPublisher commentEventPublisher) {
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
    public void dislikeComment(DislikeCommentCommand command) throws UserNotFoundException {
        if (!checkUserExistence.checkUserExistence(new CheckUserExistenceCommand(command.getUsername())).isUserExists()) {
            throw new UserNotFoundException("User not found " + command.getUsername());
        }

        //TODO implement
    }

    @Override
    public void likeComment(LikeCommentCommand command) {
        //TODO implement
    }
}
