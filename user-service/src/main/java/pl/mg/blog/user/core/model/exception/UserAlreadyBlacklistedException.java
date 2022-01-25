package pl.mg.blog.user.core.model.exception;

public class UserAlreadyBlacklistedException extends Exception {
    public UserAlreadyBlacklistedException(String username) {
        super("User already blacklisted " + username);
    }
}
