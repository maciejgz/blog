package pl.mg.blog.simulation.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import pl.mg.blog.post.CreatePostCommand;
import pl.mg.blog.user.UserDto;

@Slf4j
public class AddPostEventExecutor implements SimulationEventExecutor {

    public static final String ADD_POST_API_URL = "http://localhost:8082/api/v1/post";

    @Override
    public void execute() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        UserDto user = drawUser();
        headers.setBasicAuth(user.getUsername(), user.getPassword());
        headers.setContentType(MediaType.APPLICATION_JSON);

        Faker faker = new Faker();
        ObjectMapper mapper = new ObjectMapper();
        CreatePostCommand command = new CreatePostCommand(user.getUsername(), faker.backToTheFuture().quote(), faker.witcher().quote());
        try {
            HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(command), headers);
            restTemplate.postForEntity(ADD_POST_API_URL, request, String.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

    }

}
