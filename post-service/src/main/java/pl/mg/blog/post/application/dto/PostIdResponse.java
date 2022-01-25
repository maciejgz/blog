package pl.mg.blog.post.application.dto;

import lombok.Value;
import pl.mg.blog.post.core.model.aggregate.Post;

@Value
public class PostIdResponse {

    String postId;

    public static PostIdResponse ofPost(Post post) {
        return new PostIdResponse(post.getId().toString());
    }

}
