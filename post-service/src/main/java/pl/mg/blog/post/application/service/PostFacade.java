package pl.mg.blog.post.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.mg.blog.post.application.dto.PostIdResponse;
import pl.mg.blog.post.core.model.aggregate.Post;
import pl.mg.blog.post.core.model.command.*;
import pl.mg.blog.post.core.model.exception.*;
import pl.mg.blog.post.core.port.incoming.*;

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
    private final RemovePostLike removePostLike;

    public PostFacade(@Qualifier("createPost") CreatePost createPost,
                      @Qualifier("editPost") EditPost editPost,
                      @Qualifier("getPost") GetPost getPost,
                      @Qualifier("likePost") LikePost likePost,
                      @Qualifier("removePostLike") RemovePostLike removePostLike) {
        this.createPost = createPost;
        this.editPost = editPost;
        this.getPost = getPost;
        this.likePost = likePost;
        this.removePostLike = removePostLike;
    }

    public PostIdResponse createPost(CreatePostCommand command) throws UserNotFoundException {
        Post post = createPost.createPost(command);
        return PostIdResponse.ofPost(post);
    }

    public PostIdResponse editPost(EditPostCommand command) throws UserNotFoundException, PostNotFoundException {
        return PostIdResponse.ofPost(editPost.editPost(command));
    }

    public Post getPost(GetPostCommand command) throws PostNotFoundException {
        //TODO argument should be controller DTO instead of domain object
        //TODO move to Query Service
        //TODO return object should be DTO
        return getPost.getPost(command);
    }

    public void likePost(LikePostCommand command) throws UserNotFoundException, PostNotFoundException, PostAlreadyLikedException, UserBlacklistedException {
        //TODO argument should be controller DTO instead of domain object
        likePost.likePost(command);
    }

    public void removePostLike(RemovePostLikeCommand command) throws UserNotFoundException, PostNotFoundException, UserBlacklistedException, PostAlreadyNotLikedException {
        //TODO argument should be controller DTO instead of domain object
        removePostLike.removePostLikeCommand(command);
    }

}
