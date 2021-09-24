package pl.mg.blog.userservice.domain.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mg.blog.userservice.domain.UserDto;
import pl.mg.blog.userservice.domain.repository.UserRepository;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    private UserRepository userRepository;

    @Value(value = "${svc-user.injected}")
    private String injectedValue;

    @GetMapping(value = "")
    //TODO secure service
    //   @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> getUser() {
        log.debug("injected value {} ", injectedValue);
        return ResponseEntity.ok(new UserDto("user1", "pass1"));
    }

    //TODO implement registration endpoint

}
