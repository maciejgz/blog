package pl.mg.blog.comment.infrastructure.adapter.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mg.blog.comment.core.model.command.GetPostAuthorCommand;
import pl.mg.blog.comment.core.model.command.PostAuthorResponse;
import pl.mg.blog.comment.core.model.command.PostQueryResult;
import pl.mg.blog.comment.core.model.exception.PostNotFoundException;
import pl.mg.blog.comment.core.port.outgoing.GetPostAuthor;

@Service
@Slf4j
public class GetPostAuthorAdapter implements GetPostAuthor {

    private final PostServiceHttpClient postServiceHttpClient;

    public GetPostAuthorAdapter(PostServiceHttpClient postServiceHttpClient) {
        this.postServiceHttpClient = postServiceHttpClient;
    }

    @Override
    public PostAuthorResponse getPostAuthor(GetPostAuthorCommand command) throws PostNotFoundException {
        ResponseEntity<PostQueryResult> response = postServiceHttpClient.getPost(command.getPostId());
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            throw new PostNotFoundException("Post " + command.getPostId() + " not exists");
        }
        PostQueryResult body = response.getBody();
        if (body == null) {
            throw new PostNotFoundException("Post " + command.getPostId() + " not exists");
        }
        return new PostAuthorResponse(body.getAuthor());
    }
}
