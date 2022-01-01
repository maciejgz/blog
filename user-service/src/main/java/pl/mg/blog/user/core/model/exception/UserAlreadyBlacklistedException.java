package pl.mg.blog.user.core.model.exception;

public class UserAlreadyBlacklistedException extends RuntimeException {
    public UserAlreadyBlacklistedException(String username) {
        super("User already blacklisted " + username);
    }
}
