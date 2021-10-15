package pl.mg.blog.comment.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(name = "user-service")
public interface UserClient {

    @RequestMapping(value = "/user/{username}", method = RequestMethod.HEAD)
    ResponseEntity<Void> checkIfUserExists(@PathVariable("username") String username);

}
