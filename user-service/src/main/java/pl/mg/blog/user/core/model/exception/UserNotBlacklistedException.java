package pl.mg.blog.user.core.model.exception;

public class UserNotBlacklistedException extends Exception {
    public UserNotBlacklistedException(String username) {
        super("User is not blacklisted " + username);
    }
}
