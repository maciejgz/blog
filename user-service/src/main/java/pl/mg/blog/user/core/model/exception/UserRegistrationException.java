package pl.mg.blog.user.core.model.exception;

public class UserRegistrationException extends Exception {
    public UserRegistrationException(String username) {
        super("User cannot be registered " + username);
    }
}
