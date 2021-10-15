package pl.mg.blog.userservice.domain.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        log.info("Get user with name: {}...", username);
        UserDto userDto = UserRepository.USERS.get(username);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkIfUserExist(@PathVariable(name = "username") String username) {
        log.info("Get user with name: {}...", username);
        UserDto userDto = UserRepository.USERS.get(username);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/random")
    //TODO secure service
    //   @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> getRandomUser() throws NoSuchAlgorithmException {
        log.debug("Get random user...");
        log.info("Get random user...");
        Random rand = SecureRandom.getInstanceStrong();
        int index = rand.nextInt(UserRepository.USERS.size());
        return ResponseEntity.ok(UserRepository.USERS.get(UserRepository.USERS.keySet().toArray()[index]));
    }

    @PostMapping(value = "")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        log.debug("Register user {}", userDto);
        log.info("Register user {}", userDto);
        UserRepository.USERS.put(userDto.getUsername(), userDto);
        return ResponseEntity.ok(userDto);
    }

}
