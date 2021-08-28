package pl.mg.blog.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    private UserRepository userRepository;

    @GetMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> getUser() {
        return ResponseEntity.ok(new UserDto("user1", "pass1"));
    }
    
    //TODO implement registration endpoint

}
