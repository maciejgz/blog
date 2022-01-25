package pl.mg.blog.comment.infrastructure.adapter.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mg.blog.comment.core.model.command.CheckPostExistenceCommand;
import pl.mg.blog.comment.core.model.command.CheckPostExistenceResponse;
import pl.mg.blog.comment.core.port.outgoing.CheckPostExistence;


@Service
@Slf4j
public class CheckPostExistenceAdapter implements CheckPostExistence {

    private final PostServiceHttpClient postServiceHttpClient;

    public CheckPostExistenceAdapter(PostServiceHttpClient postServiceHttpClient) {
        this.postServiceHttpClient = postServiceHttpClient;
    }

    @Override
    public CheckPostExistenceResponse checkPostExistence(CheckPostExistenceCommand command) {
        ResponseEntity<Void> response = postServiceHttpClient.checkIfPostExists(command.getPostId());
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            return new CheckPostExistenceResponse(command.getPostId(), false);
        } else {
            return new CheckPostExistenceResponse(command.getPostId(), true);
        }
    }
}
