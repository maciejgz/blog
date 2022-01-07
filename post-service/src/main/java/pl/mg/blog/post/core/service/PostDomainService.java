package pl.mg.blog.post.core.service;

import pl.mg.blog.post.core.model.aggregate.Post;
import pl.mg.blog.post.core.model.command.*;
import pl.mg.blog.post.core.port.incoming.*;
import pl.mg.blog.post.core.port.outgoing.PostDatabase;
import pl.mg.blog.post.core.port.outgoing.PostEventPublisher;

public class PostDomainService implements CreatePost, EditPost, GetPost, LikePost, RemovePostLike {

    private final PostDatabase postDatabase;
    private final PostEventPublisher postEventPublisher;

    public PostDomainService(PostDatabase postDatabase, PostEventPublisher postEventPublisher) {
        this.postDatabase = postDatabase;
        this.postEventPublisher = postEventPublisher;
    }


    @Override
    public void createPost(CreatePostCommand command) {
        //TODO implement
    }

    @Override
    public void editPost(EditPostCommand command) {
        //TODO implement
    }

    @Override
    public Post getPost(GetPostCommand command) {
        //TODO implement
        return null;
    }

    @Override
    public void likePost(LikePostCommand command) {
        //TODO implement
    }

    @Override
    public void removePostLikeCommand(RemovePostLikeCommand command) {
        //TODO implement
    }
}
