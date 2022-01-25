package pl.mg.blog.post.core.model.exception;

public class PostAlreadyLikedException extends Exception {
    public PostAlreadyLikedException() {
        super();
    }

    public PostAlreadyLikedException(String message) {
        super(message);
    }

    public PostAlreadyLikedException(String message, Throwable cause) {
        super(message, cause);
    }
}
