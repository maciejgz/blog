package pl.mg.blog.comment.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import pl.mg.blog.comment.application.service.CommentApplicationService;

@Controller
@Slf4j
public class CommentController {

    private final CommentApplicationService commentApplicationService;

    public CommentController(CommentApplicationService commentApplicationService) {
        this.commentApplicationService = commentApplicationService;
    }
}
