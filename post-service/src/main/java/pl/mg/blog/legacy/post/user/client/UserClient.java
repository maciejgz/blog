package pl.mg.blog.legacy.post.user.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User service client service with fallback method allows creating post even if user service is not available.
 */
@Service
@FeignClient(name = "user-service")
/*, fallback = UserClient.UserServiceClientFallback.class*/
public interface UserClient {

    Logger log = LoggerFactory.getLogger(UserClient.class);

    @RequestMapping(value = "/user/{username}", method = RequestMethod.HEAD)
    //@Retry(name = "user-service-retry", fallbackMethod = "checkIfUserExistsFallback")
    @CircuitBreaker(name = "user-service-cb", fallbackMethod = "checkIfUserExistsFallback")
    ResponseEntity<Void> checkIfUserExists(@PathVariable("username") String username);

    default ResponseEntity<Void> checkIfUserExistsFallback(String username, Throwable t) {
        log.debug("checkIfUserExistsFallback called for user {}", username);
        return ResponseEntity.ok().build();
    }

}
