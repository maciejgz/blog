package pl.mg.blog.comment.legacy.exception;

public class CommentAlreadyDislikedException extends Exception {
    public CommentAlreadyDislikedException() {
        super();
    }

    public CommentAlreadyDislikedException(String message) {
        super(message);
    }

    public CommentAlreadyDislikedException(String message, Throwable cause) {
        super(message, cause);
    }
}
