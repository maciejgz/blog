package pl.mg.blog.post.core.model.exception;

public class UserBlacklistedException extends Exception {
    public UserBlacklistedException() {
        super();
    }

    public UserBlacklistedException(String message) {
        super(message);
    }

    public UserBlacklistedException(String message, Throwable cause) {
        super(message, cause);
    }
}
