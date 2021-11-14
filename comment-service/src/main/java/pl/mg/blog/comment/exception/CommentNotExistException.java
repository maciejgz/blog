package pl.mg.blog.comment.exception;

public class CommentNotExistException extends Exception {
    public CommentNotExistException() {
        super();
    }

    public CommentNotExistException(String message) {
        super(message);
    }

    public CommentNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
