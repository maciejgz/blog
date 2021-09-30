package pl.mg.blog.post.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mg.blog.post.dto.CreatePostCommand;
import pl.mg.blog.post.dto.EditPostCommand;
import pl.mg.blog.post.dto.PostNotFoundException;

@Service
@Slf4j
public class PostCommandServiceImpl implements PostCommandService {


    @Override
    public void createPost(CreatePostCommand command) {

    }

    @Override
    public void editPost(EditPostCommand command) throws PostNotFoundException {

    }
}
