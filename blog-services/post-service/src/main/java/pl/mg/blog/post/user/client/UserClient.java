package pl.mg.blog.post.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * User service client service with fallback method allows creating post even if user service is not available.
 */
@Service
@FeignClient(name = "user-service", fallback = UserClient.UserServiceClientFallback.class)
public interface UserClient {

    @RequestMapping(value = "/user/{username}", method = RequestMethod.HEAD)
    ResponseEntity<Void> checkIfUserExists(@PathVariable("username") String username);

    @Component
    class UserServiceClientFallback implements UserClient {

        @Override
        @RequestMapping(value = "/user/{username}", method = RequestMethod.HEAD)
        public ResponseEntity<Void> checkIfUserExists(@PathVariable("username") String username) {
            return ResponseEntity.ok().build();
        }
    }
}
