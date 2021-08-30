package pl.mg.blog.simulation.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.mg.blog.comment.AddCommentCommand;
import pl.mg.blog.post.PostNotFoundException;
import pl.mg.blog.post.PostQueryResult;
import pl.mg.blog.user.UserDto;

@Slf4j
public class AddCommentEventExecutor implements SimulationEventExecutor {


    @Override
    public void execute() {
        UserDto user = drawUser();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PostQueryResult post = drawPost(user);
            AddCommentCommand command = createFakeCommentCommand(user, post);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(user.getUsername(), user.getPassword());
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(command), headers);
            restTemplate.postForEntity(BASE_API_URL + ADD_COMMENT_API_URL, request, String.class);
        } catch (RestClientException | JsonProcessingException | PostNotFoundException e) {
            log.error(e.getMessage(), e);
        }
    }

    private AddCommentCommand createFakeCommentCommand(UserDto user, PostQueryResult post) {
        AddCommentCommand command = new AddCommentCommand();
        command.setUsername(user.getUsername());
        command.setPostId(post.getId());
        command.setContent(Faker.instance().shakespeare().kingRichardIIIQuote());
        return command;
    }

    private PostQueryResult drawPost(UserDto user) throws PostNotFoundException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(user.getUsername(), user.getPassword());
            headers.setBasicAuth(user.getUsername(), user.getPassword());
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<PostQueryResult> queryResponse = restTemplate.exchange(
                    BASE_API_URL + RANDOM_POST_API_URL, HttpMethod.GET, request, PostQueryResult.class);
            return queryResponse.getBody();
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            throw new PostNotFoundException("Post not found");
        }
    }
}
