package pl.mg.blog.post.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mg.blog.post.application.dto.PostDto;
import pl.mg.blog.post.core.model.command.*;
import pl.mg.blog.post.core.port.incoming.CreatePost;
import pl.mg.blog.post.core.port.incoming.EditPost;
import pl.mg.blog.post.core.port.incoming.GetPost;
import pl.mg.blog.post.core.port.incoming.LikePost;

/**
 * Facade service - integrates all adapters, add tech specific layer like transactions.
 */
@Slf4j
@Service
public class PostFacade {

    private final CreatePost createPost;
    private final EditPost editPost;
    private final GetPost getPost;
    private final LikePost likePost;

    public PostFacade(CreatePost createPost, EditPost editPost, GetPost getPost, LikePost likePost) {
        this.createPost = createPost;
        this.editPost = editPost;
        this.getPost = getPost;
        this.likePost = likePost;
    }


    public PostDto createPost(CreatePostCommand command) {
        //TODO argument should be controller DTO instead of domain object
        //TODO implement
        return null;
    }

    public PostDto editPost(EditPostCommand command) {
        //TODO argument should be controller DTO instead of domain object
        //TODO implement
        return null;
    }

    public PostDto getPost(GetPostCommand command) {
        //TODO argument should be controller DTO instead of domain object
        //TODO implement
        return null;
    }

    public void likePost(LikePostCommand command) {
        //TODO argument should be controller DTO instead of domain object
        //TODO implement
    }

    public void removePostLike(RemovePostLikeCommand command) {
        //TODO argument should be controller DTO instead of domain object
        //TODO implement
    }

}
