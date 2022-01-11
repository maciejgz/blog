package pl.mg.blog.comment.legacy.post.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(name = "post-service")
public interface PostClient {

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.HEAD)
    @CircuitBreaker(name = "post-service-cb", fallbackMethod = "checkIfPostExistsFallback")
    ResponseEntity<Void> checkIfPostExists(@PathVariable("postId") String postId);

    default ResponseEntity<Void> checkIfPostExistsFallback(String postId, Throwable t) {
        return ResponseEntity.ok().build();
    }

}
