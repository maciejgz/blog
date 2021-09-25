package pl.mg.blog.userservice.domain.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mg.blog.userservice.domain.UserDto;
import pl.mg.blog.userservice.domain.repository.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @GetMapping(value = "")
    //TODO secure service
    //   @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> getUser() {
        return ResponseEntity.ok(new UserDto("user1", "pass1"));
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDto> getUserByScreenname(@PathVariable(name = "username") String username) {
        return ResponseEntity.ok(UserRepository.USERS.get(username));
    }

    @GetMapping(value = "/random")
    //TODO secure service
    //   @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> getRandomUser() throws NoSuchAlgorithmException {
        Random rand = SecureRandom.getInstanceStrong();
        int index = rand.nextInt(UserRepository.USERS.size());
        return ResponseEntity.ok(UserRepository.USERS.get(UserRepository.USERS.keySet().toArray()[index]));
    }

    @PostMapping(value = "")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        UserRepository.USERS.put(userDto.getUsername(), userDto);
        return ResponseEntity.ok(userDto);
    }

}
