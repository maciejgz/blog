package pl.mg.blog.post.security;

import org.springframework.stereotype.Component;

@Component("postAccessService")
public class PostAccessService {

    public boolean hasPermission(String postId) {
        return false;
    }
}
