package pl.mg.blog.post.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mg.blog.post.core.model.aggregate.Post;
import pl.mg.blog.post.core.model.exception.PostNotFoundException;
import pl.mg.blog.post.core.port.outgoing.PostDatabase;

@RestController
@RequestMapping(value = "/post")
@Slf4j
public class PostQueryController {

    private final PostDatabase postDatabase;

    public PostQueryController(PostDatabase postDatabase) {
        this.postDatabase = postDatabase;
    }

    @GetMapping(value = "/random")
    public ResponseEntity<Post> getRandomPost() throws PostNotFoundException {
        return ResponseEntity.ok(
                postDatabase.getRandomPost().orElseThrow(() -> new PostNotFoundException("Post nt found")));
    }

}
