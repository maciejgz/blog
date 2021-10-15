package pl.mg.blog.post.service;

import org.springframework.security.access.prepost.PreAuthorize;
import pl.mg.blog.post.dto.*;

import javax.validation.Valid;

public interface PostCommandService {

    public PostQueryResult createPost(@Valid CreatePostCommand command) throws UserNotFoundException;

    @PreAuthorize("@postAccessService.hasPermission(#command.username, #command.id)")
    public void editPost(@Valid EditPostCommand command) throws PostNotFoundException, UserNotFoundException;
}
