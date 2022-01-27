package pl.mg.blog.simulator.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.mg.blog.simulator.commons.BlacklistUserCommand;
import pl.mg.blog.simulator.commons.UserDto;

@Slf4j
public class BlackListUserEventExecutor implements SimulationEventExecutor {

    @Override
    public void execute() {
        UserDto user = drawUser();
        UserDto userToBeBlacklisted = drawUser();

        ObjectMapper mapper = new ObjectMapper();
        try {
            BlacklistUserCommand command = new BlacklistUserCommand(user.getUsername(),
                    userToBeBlacklisted.getUsername());
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
//            headers.setBasicAuth(user.getUsername(), user.getPassword());
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(command), headers);
            restTemplate.postForEntity(BASE_API_URL + BLACKLIST_USER_API_URL, request, String.class);
        } catch (RestClientException | JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

}
