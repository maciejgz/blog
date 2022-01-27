package pl.mg.blog.simulator.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.mg.blog.simulator.commons.RegisterUserCommand;
import pl.mg.blog.simulator.commons.UserDto;

@Slf4j
public class RegisterUserEventExecutor implements SimulationEventExecutor {

    @Override
    public void execute() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            RegisterUserCommand command = new RegisterUserCommand(Faker.instance().name().username(),
                    Faker.instance().name().lastName());
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(command), headers);
            restTemplate.postForEntity(BASE_API_URL + REGISTER_USER_API_URL, request, String.class);
        } catch (RestClientException | JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

}
