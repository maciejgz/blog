package pl.mg.blog.post.service;

import org.springframework.security.access.prepost.PreAuthorize;
import pl.mg.blog.post.dto.CreatePostCommand;
import pl.mg.blog.post.dto.EditPostCommand;
import pl.mg.blog.post.dto.PostNotFoundException;

import javax.validation.Valid;

public interface PostCommandService {

    public void createPost(@Valid CreatePostCommand command);

    @PreAuthorize("@postAccessService.hasPermission(#command.username, #command.id)")
    public void editPost(@Valid EditPostCommand command) throws PostNotFoundException;
}
