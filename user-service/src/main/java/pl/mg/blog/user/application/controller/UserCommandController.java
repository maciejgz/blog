package pl.mg.blog.user.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mg.blog.user.application.service.UserFacade;
import pl.mg.blog.user.core.model.User;
import pl.mg.blog.user.core.model.command.BlacklistUserCommand;
import pl.mg.blog.user.core.model.command.RegisterUserCommand;
import pl.mg.blog.user.core.model.command.RemoveUserFromBlacklistCommand;
import pl.mg.blog.user.core.model.exception.UserAlreadyBlacklistedException;
import pl.mg.blog.user.core.model.exception.UserNotBlacklistedException;
import pl.mg.blog.user.core.model.exception.UserNotFoundException;
import pl.mg.blog.user.core.model.exception.UserRegistrationException;
import pl.mg.blog.user.core.model.response.IsUserBlacklistedResponse;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserCommandController {

    private final UserFacade userFacade;

    public UserCommandController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<User> getUserByScreenname(@PathVariable(name = "username") String username)
            throws UserNotFoundException {
        log.info("Get user with name: {}...", username);
        User user = userFacade.getUser(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserCommand command)
            throws UserRegistrationException {
        log.debug("Register user {}", command);
        User user = userFacade.registerUser(command);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/blacklist")
    public ResponseEntity<Void> blacklistUser(@RequestBody BlacklistUserCommand command)
            throws UserNotFoundException, UserAlreadyBlacklistedException {
        log.debug("Blacklist user {}", command);
        userFacade.blacklistUser(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/blacklist")
    public ResponseEntity<Void> removeUserFromBlacklist(@RequestBody RemoveUserFromBlacklistCommand command)
            throws UserNotFoundException, UserNotBlacklistedException {
        log.debug("RemoveUserFromBlacklist user {}", command);
        userFacade.removeUserFromBlacklist(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/blacklist/{username}/{blacklistedUsername}")
    public ResponseEntity<IsUserBlacklistedResponse> checkIfUserBlacklisted(@PathVariable("username") String username,
            @PathVariable("blacklistedUsername") String blacklistedUsername)
            throws UserNotFoundException, UserAlreadyBlacklistedException {
        log.debug("CheckIfUserBlacklisted. User {}, blacklisted user {}", username, blacklistedUsername);
        IsUserBlacklistedResponse isUserBlacklistedResponse = userFacade.checkIfUserIsBlacklisted(username,
                blacklistedUsername);
        return ResponseEntity.ok(isUserBlacklistedResponse);
    }
}
