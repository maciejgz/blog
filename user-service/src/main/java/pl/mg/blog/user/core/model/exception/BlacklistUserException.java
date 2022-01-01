package pl.mg.blog.user.core.model.exception;

public class BlacklistUserException extends RuntimeException {
    public BlacklistUserException(String username) {
        super("User cannot be blacklisted " + username);
    }
}
