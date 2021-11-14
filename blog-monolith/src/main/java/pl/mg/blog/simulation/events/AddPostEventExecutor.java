package pl.mg.blog.simulation.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.mg.blog.post.CreatePostCommand;
import pl.mg.blog.user.UserDto;

@Slf4j
public class AddPostEventExecutor implements SimulationEventExecutor {

    @Override
    public void execute() {
        UserDto user = drawUser();
        CreatePostCommand command = createFakePostCommand(user);
        ObjectMapper mapper = new ObjectMapper();
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(user.getUsername(), user.getPassword());
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(command), headers);
            restTemplate.postForEntity(BASE_API_URL + ADD_POST_API_URL, request, String.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

    private CreatePostCommand createFakePostCommand(UserDto user) {
        Faker faker = new Faker();
        return new CreatePostCommand(user.getUsername(), faker.backToTheFuture().quote(),
                faker.witcher().quote());
    }

}
