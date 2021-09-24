package pl.mg.blog.simulator.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.mg.blog.simulator.commons.CommentQueryResult;
import pl.mg.blog.simulator.commons.DislikeCommentCommand;
import pl.mg.blog.simulator.commons.PostNotFoundException;
import pl.mg.blog.simulator.commons.UserDto;

@Slf4j
public class DislikeCommentEventExecutor implements SimulationEventExecutor {

    @Override
    public void execute() {
        UserDto user = drawUser();
        ObjectMapper mapper = new ObjectMapper();
        try {
            CommentQueryResult comment = drawComment(user);
            DislikeCommentCommand command = createFakeDislikeCommand(comment);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(user.getUsername(), user.getPassword());
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(command), headers);
            restTemplate.postForEntity(BASE_API_URL + DISLIKE_COMMENT_API_URL, request, String.class);
        } catch (RestClientException | JsonProcessingException | PostNotFoundException e) {
            log.error(e.getMessage(), e);
        }
    }

    private DislikeCommentCommand createFakeDislikeCommand(CommentQueryResult comment) {
        DislikeCommentCommand likeCommentCommand = new DislikeCommentCommand();
        likeCommentCommand.setCommentId(comment.getId());
        return likeCommentCommand;
    }

    private CommentQueryResult drawComment(UserDto user) throws PostNotFoundException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(user.getUsername(), user.getPassword());
            headers.setBasicAuth(user.getUsername(), user.getPassword());
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<CommentQueryResult> queryResponse = restTemplate.exchange(
                    BASE_API_URL + RANDOM_COMMENT_API_URL, HttpMethod.GET, request, CommentQueryResult.class);
            return queryResponse.getBody();
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            throw new PostNotFoundException("Comment not found");
        }
    }
}
