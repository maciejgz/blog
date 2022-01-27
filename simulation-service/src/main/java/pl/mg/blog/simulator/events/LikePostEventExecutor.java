package pl.mg.blog.simulator.events;

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
import pl.mg.blog.simulator.commons.AddCommentCommand;
import pl.mg.blog.simulator.commons.LikePostCommand;
import pl.mg.blog.simulator.commons.PostNotFoundException;
import pl.mg.blog.simulator.commons.PostQueryResult;
import pl.mg.blog.simulator.commons.UserDto;

@Slf4j
public class LikePostEventExecutor implements SimulationEventExecutor {

    @Override
    public void execute() {
        UserDto user = drawUser();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PostQueryResult post = drawPost(user);
            LikePostCommand command = new LikePostCommand(post.getId(), user.getUsername());
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(user.getUsername(), user.getPassword());
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(command), headers);
            restTemplate.postForEntity(
                    BASE_API_URL + ADD_POST_API_URL + "/" + post.getId() + "/like/" + user.getUsername(), request,
                    String.class);
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
