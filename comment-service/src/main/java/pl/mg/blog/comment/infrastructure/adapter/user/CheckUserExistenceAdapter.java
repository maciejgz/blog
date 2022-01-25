package pl.mg.blog.comment.infrastructure.adapter.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mg.blog.comment.core.model.command.CheckUserExistenceCommand;
import pl.mg.blog.comment.core.model.command.CheckUserExistenceResult;
import pl.mg.blog.comment.core.port.outgoing.CheckUserExistence;

@Service
public class CheckUserExistenceAdapter implements CheckUserExistence {

    private final UserServiceHttpClient userServiceHttpClient;

    public CheckUserExistenceAdapter(UserServiceHttpClient userServiceHttpClient) {
        this.userServiceHttpClient = userServiceHttpClient;
    }

    @Override
    public CheckUserExistenceResult checkUserExistence(CheckUserExistenceCommand command) {
        ResponseEntity<Void> response = userServiceHttpClient.checkIfUserExists(command.getUsername());
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            return new CheckUserExistenceResult(false);
        } else {
            return new CheckUserExistenceResult(true);
        }
    }
}
