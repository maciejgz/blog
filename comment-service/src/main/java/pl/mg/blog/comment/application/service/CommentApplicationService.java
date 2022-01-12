package pl.mg.blog.comment.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mg.blog.comment.application.model.CommentQueryResult;
import pl.mg.blog.comment.core.model.command.AddCommentCommand;
import pl.mg.blog.comment.core.model.command.DislikeCommentCommand;
import pl.mg.blog.comment.core.model.command.LikeCommentCommand;
import pl.mg.blog.comment.core.model.exception.CommentNotExistException;
import pl.mg.blog.comment.core.port.incoming.AddComment;
import pl.mg.blog.comment.core.port.incoming.DislikeComment;
import pl.mg.blog.comment.core.port.incoming.GetComment;
import pl.mg.blog.comment.core.port.incoming.LikeComment;

@Service
@Slf4j
public class CommentApplicationService {

    private final GetComment getComment;
    private final AddComment addComment;
    private final LikeComment likeComment;
    private final DislikeComment dislikeComment;

    public CommentApplicationService(GetComment getComment, AddComment addComment,
                                     LikeComment likeComment, DislikeComment dislikeComment) {
        this.getComment = getComment;
        this.addComment = addComment;
        this.likeComment = likeComment;
        this.dislikeComment = dislikeComment;
    }

    public CommentQueryResult addComment(AddCommentCommand command) {
        //TODO implement
        return null;
    }

    public void likeComment(LikeCommentCommand command) {
        //TODO implement
    }

    public void dislikeComment(DislikeCommentCommand command) {
        //TODO implement
    }

    public CommentQueryResult getComment(String commentId) throws CommentNotExistException {
        //TODO implement
        return null;
    }
}
