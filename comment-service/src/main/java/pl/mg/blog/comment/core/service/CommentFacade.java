package pl.mg.blog.comment.core.service;

import pl.mg.blog.comment.core.model.Comment;
import pl.mg.blog.comment.core.model.command.AddCommentCommand;
import pl.mg.blog.comment.core.model.command.DislikeCommentCommand;
import pl.mg.blog.comment.core.model.command.LikeCommentCommand;
import pl.mg.blog.comment.core.port.incoming.AddComment;
import pl.mg.blog.comment.core.port.incoming.DislikeComment;
import pl.mg.blog.comment.core.port.incoming.LikeComment;
import pl.mg.blog.comment.core.port.outgoing.CheckBlacklist;
import pl.mg.blog.comment.core.port.outgoing.CheckPostExistence;
import pl.mg.blog.comment.core.port.outgoing.CheckUserExistence;
import pl.mg.blog.comment.core.port.outgoing.CommentDatabase;

/**
 * Facade of all incoming comment ports
 */
public class CommentFacade implements AddComment, DislikeComment, LikeComment {

    private final CheckBlacklist checkBlacklist;
    private final CheckPostExistence checkPostExistence;
    private final CheckUserExistence checkUserExistence;
    private final CommentDatabase commentDatabase;

    public CommentFacade(CheckBlacklist checkBlacklist, CheckPostExistence checkPostExistence, CheckUserExistence checkUserExistence, CommentDatabase commentDatabase) {
        this.checkBlacklist = checkBlacklist;
        this.checkPostExistence = checkPostExistence;
        this.checkUserExistence = checkUserExistence;
        this.commentDatabase = commentDatabase;
    }

    @Override
    public Comment addComment(AddCommentCommand command) {
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
