package pl.mg.blog.post.core.port.outgoing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mg.blog.post.infrastructure.adapter.IsUserBlacklistedResponse;

public interface UserServiceClient {

    ResponseEntity<Void> checkIfUserExists(@PathVariable("username") String username);

    ResponseEntity<IsUserBlacklistedResponse> checkIfUserIsBlacklisted(@PathVariable("username") String username,
                                                                       @PathVariable("blacklistedUsername") String blacklistedUsername);

}
