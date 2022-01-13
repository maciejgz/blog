package pl.mg.blog.comment.core.service;

import pl.mg.blog.comment.core.model.Comment;
import pl.mg.blog.comment.core.model.command.*;
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

    public CommentFacade(CheckBlacklist checkBlacklist, CheckPostExistence checkPostExistence,
                         CheckUserExistence checkUserExistence, GetPostAuthor getPostAuthor, CommentDatabase commentDatabase) {
        this.checkBlacklist = checkBlacklist;
        this.checkPostExistence = checkPostExistence;
        this.checkUserExistence = checkUserExistence;
        this.getPostAuthor = getPostAuthor;
        this.commentDatabase = commentDatabase;
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
        //TODO implement


        return null;
    }

    @Override
    public void dislikeComment(DislikeCommentCommand command) {
        //TODO implement
    }

    @Override
    public void likeComment(LikeCommentCommand command) {
        //TODO implement
    }
}
