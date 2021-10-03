package pl.mg.blog.post.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mg.blog.commons.UserDto;

@Service
@FeignClient(name = "user-service")
public interface UserClientService {

    @GetMapping("/user/{username}")
    UserDto findByUsername(@PathVariable("username") String username);

}
