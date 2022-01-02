package pl.mg.blog.user.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mg.blog.user.core.model.User;
import pl.mg.blog.user.core.model.command.BlacklistUserCommand;
import pl.mg.blog.user.core.model.command.RegisterUserCommand;
import pl.mg.blog.user.core.model.command.RemoveUserFromBlacklistCommand;
import pl.mg.blog.user.core.model.exception.UserAlreadyBlacklistedException;
import pl.mg.blog.user.core.model.exception.UserNotBlacklistedException;
import pl.mg.blog.user.core.model.exception.UserNotFoundException;
import pl.mg.blog.user.core.model.exception.UserRegistrationException;
import pl.mg.blog.user.core.port.incoming.BlacklistUser;
import pl.mg.blog.user.core.port.incoming.GetUser;
import pl.mg.blog.user.core.port.incoming.RegisterUser;
import pl.mg.blog.user.core.port.incoming.RemoveUserFromBlacklist;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    private final GetUser getUserAdapter;
    private final RegisterUser registerUserAdapter;
    private final BlacklistUser blacklistUserAdapter;
    private final RemoveUserFromBlacklist removeUserFromBlacklistAdapter;

    public UserController(@Qualifier("getUser") GetUser getUser,
                          @Qualifier("registerUser") RegisterUser registerUser,
                          @Qualifier("blacklistUser") BlacklistUser blacklistUser,
                          @Qualifier("removeUserFromBlacklist") RemoveUserFromBlacklist removeUserFromBlacklist) {
        this.getUserAdapter = getUser;
        this.registerUserAdapter = registerUser;
        this.blacklistUserAdapter = blacklistUser;
        this.removeUserFromBlacklistAdapter = removeUserFromBlacklist;
    }


    @GetMapping(value = "/{username}")
    public ResponseEntity<User> getUserByScreenname(@PathVariable(name = "username") String username) throws UserNotFoundException {
        log.info("Get user with name: {}...", username);
        User user = getUserAdapter.getUser(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/random")
    //TODO secure service
    //   @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getRandomUser() {
        log.debug("Get random user...");
        //TODO implement
        return ResponseEntity.ok(null);
    }

    @PostMapping(value = "")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserCommand command) throws UserRegistrationException {
        log.debug("Register user {}", command);
        User user = registerUserAdapter.registerUser(command);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/blacklist")
    public ResponseEntity<Void> blacklistUser(@RequestBody BlacklistUserCommand command) throws UserNotFoundException, UserAlreadyBlacklistedException {
        log.debug("Blacklist user {}", command);
        blacklistUserAdapter.blacklistUser(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/blacklist")
    public ResponseEntity<Void> removeUserFromBlacklist(@RequestBody RemoveUserFromBlacklistCommand command) throws UserNotFoundException, UserNotBlacklistedException {
        log.debug("RemoveUserFromBlacklist user {}", command);
        removeUserFromBlacklistAdapter.removeUserFromBlacklist(command);
        return ResponseEntity.ok().build();
    }
}
