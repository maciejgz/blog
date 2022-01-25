package pl.mg.blog.user.core.model.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String username) {
        super("User not found: " + username);
    }
}
