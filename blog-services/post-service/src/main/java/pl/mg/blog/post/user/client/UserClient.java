package pl.mg.blog.post.user.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @RequestMapping(value = "/user/{username}", method = RequestMethod.HEAD)
    //@Retry(name = "user-service-retry", fallbackMethod = "checkIfUserExistsFallback")
    @CircuitBreaker(name = "user-service-cb", fallbackMethod = "checkIfUserExistsFallback")
    ResponseEntity<Void> checkIfUserExists(@PathVariable("username") String username);

    default ResponseEntity<Void> checkIfUserExistsFallback(String username, Throwable t) {
        return ResponseEntity.ok().build();
    }

}
