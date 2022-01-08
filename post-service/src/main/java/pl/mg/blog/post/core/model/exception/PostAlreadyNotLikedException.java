package pl.mg.blog.post.core.model.exception;

public class PostAlreadyNotLikedException extends Exception {
    public PostAlreadyNotLikedException() {
        super();
    }

    public PostAlreadyNotLikedException(String message) {
        super(message);
    }

    public PostAlreadyNotLikedException(String message, Throwable cause) {
        super(message, cause);
    }
}
