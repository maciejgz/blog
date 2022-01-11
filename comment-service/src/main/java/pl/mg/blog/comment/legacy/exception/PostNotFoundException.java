package pl.mg.blog.comment.legacy.exception;

public class PostNotFoundException extends Exception {
    public PostNotFoundException() {
        super();
    }

    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
