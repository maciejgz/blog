package pl.mg.blog.user.core.model.exception;

public class RemoveUserFromBlacklistException extends RuntimeException {
    public RemoveUserFromBlacklistException(String username) {
        super("User cannot be whitelisted " + username);
    }
}
