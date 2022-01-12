package pl.mg.blog.comment.core.model.exception;

public class CommentAlreadyLikedException extends Exception {
    public CommentAlreadyLikedException() {
        super();
    }

    public CommentAlreadyLikedException(String message) {
        super(message);
    }

    public CommentAlreadyLikedException(String message, Throwable cause) {
        super(message, cause);
    }
}
