package pl.mg.blog.post.infrastructure.adapter;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.mg.blog.post.core.port.outgoing.UserServiceClient;

/**
 * User service client service with fallback method allows creating post even if user service is not available.
 */
@Service
@FeignClient(name = "user-service")
/*, fallback = UserClient.UserServiceClientFallback.class*/
public interface UserClientAdapter extends UserServiceClient {

    Logger log = LoggerFactory.getLogger(UserClientAdapter.class);

    @Override
    @RequestMapping(value = "/user/{username}", method = RequestMethod.HEAD)
    //@Retry(name = "user-service-retry", fallbackMethod = "checkIfUserExistsFallback")
    @CircuitBreaker(name = "user-service-cb", fallbackMethod = "checkIfUserExistsFallback")
    ResponseEntity<Void> checkIfUserExists(@PathVariable("username") String username);

    @Override
    @GetMapping(value = "/user/blacklist/{username}/{blacklistedUsername}")
    //@Retry(name = "user-service-retry", fallbackMethod = "checkIfUserBlacklistedFallback")
    @CircuitBreaker(name = "user-service-cb", fallbackMethod = "checkIfUserBlacklistedFallback")
    ResponseEntity<IsUserBlacklistedResponse> checkIfUserIsBlacklisted(@PathVariable("username") String username,
                                                                       @PathVariable("blacklistedUsername") String blacklistedUsername);

    default ResponseEntity<Void> checkIfUserExistsFallback(String username, Throwable t) {
        log.debug("checkIfUserExistsFallback called for user {}", username);
        return ResponseEntity.ok().build();
    }

    default ResponseEntity<IsUserBlacklistedResponse> checkIfUserBlacklistedFallback(String username, String blacklistedUsername, Throwable t) {
        log.debug("checkIfUserExistsFallback called for user {}", username);
        return ResponseEntity.ok(new IsUserBlacklistedResponse(username, blacklistedUsername, true));
    }

}
