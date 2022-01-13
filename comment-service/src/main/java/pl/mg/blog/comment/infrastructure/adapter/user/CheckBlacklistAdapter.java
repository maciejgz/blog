package pl.mg.blog.comment.infrastructure.adapter.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mg.blog.comment.core.model.command.CheckBlacklistCommand;
import pl.mg.blog.comment.core.model.command.CheckBlacklistResult;
import pl.mg.blog.comment.core.model.exception.UserNotFoundException;
import pl.mg.blog.comment.core.port.outgoing.CheckBlacklist;

@Service
public class CheckBlacklistAdapter implements CheckBlacklist {

    private final UserServiceHttpClient userServiceHttpClient;

    public CheckBlacklistAdapter(UserServiceHttpClient userServiceHttpClient) {
        this.userServiceHttpClient = userServiceHttpClient;
    }

    @Override
    public CheckBlacklistResult checkBlacklist(CheckBlacklistCommand command) throws UserNotFoundException {
        ResponseEntity<CheckBlacklistResult> response = userServiceHttpClient.checkIfUserIsBlacklisted(command.getUsername(), command.getBlacklistedUser());
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            throw new UserNotFoundException("User " + command.getBlacklistedUser() + " not exists");
        }
        CheckBlacklistResult body = response.getBody();
        if (body == null) {
            throw new UserNotFoundException("User " + command.getBlacklistedUser() + " not exists");
        }
        return new CheckBlacklistResult(command.getUsername(), command.getBlacklistedUser(), body.isBlacklisted());
    }
}
