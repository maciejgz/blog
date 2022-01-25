package pl.mg.blog.comment.legacy.exception;

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
