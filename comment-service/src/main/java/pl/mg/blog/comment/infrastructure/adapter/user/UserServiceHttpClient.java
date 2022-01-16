package pl.mg.blog.comment.infrastructure.adapter.user;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.mg.blog.comment.core.model.command.CheckBlacklistResult;

@Service
@FeignClient(name = "user-service")
public interface UserServiceHttpClient {

    @RequestMapping(value = "/user/{username}", method = RequestMethod.HEAD)
    @CircuitBreaker(name = "user-service-cb", fallbackMethod = "checkIfUserExistsFallback")
    ResponseEntity<Void> checkIfUserExists(@PathVariable("username") String username);

    default ResponseEntity<Void> checkIfUserExistsFallback(String username, Throwable t) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/user/blacklist/{username}/{blacklistedUsername}")
    //@Retry(name = "user-service-retry", fallbackMethod = "checkIfUserBlacklistedFallback")
    @CircuitBreaker(name = "user-service-cb", fallbackMethod = "checkIfUserBlacklistedFallback")
    ResponseEntity<CheckBlacklistResult> checkIfUserIsBlacklisted(@PathVariable("username") String username,
                                                                  @PathVariable("blacklistedUsername") String blacklistedUsername);

    default ResponseEntity<CheckBlacklistResult> checkIfUserBlacklistedFallback(String username, String blacklistedUsername, Throwable t) {
        return ResponseEntity.ok(new CheckBlacklistResult(username, blacklistedUsername, true));
    }
}
