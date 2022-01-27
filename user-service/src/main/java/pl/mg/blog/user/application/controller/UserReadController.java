package pl.mg.blog.user.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mg.blog.user.core.model.User;
import pl.mg.blog.user.core.port.outgoing.UserDatabase;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserReadController {

    //TODO replace with read version database without controller
    private final UserDatabase userDatabase;

    public UserReadController(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    @GetMapping(value = "/random")
    public ResponseEntity<User> getRandomUser() {
        log.debug("Get random user...");
        return ResponseEntity.ok(userDatabase.getRandomUser().orElse(null));
    }
}
