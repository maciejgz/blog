package pl.mg.blog.comment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentQueryResult addComment(@Valid AddCommentCommand command) {
        //TODO verify post existance in post microservice using HEAD request: HEAD  post/{postId}.
        // User is verified in controller based on the token
        Comment comment = new Comment(command);
        Instant created = Instant.now();
        comment.setCreated(created);
        Comment savedComment = commentRepository.save(comment);
        //TODO push notification about comment
        return new CommentQueryResult(savedComment);
    }

    @Override
    public LikeCommentResponse likeComment(LikeCommentCommand command) throws Exception {
        Optional<Comment> comment = commentRepository.findById(command.getCommentId());
        if (comment.isPresent()) {
            if (comment.get().getLikes() == null) {
                comment.get().setLikes(new HashSet<>());
            }
            Instant date = Instant.now();
            comment.get().getLikes().add(new Like(command.getUsername(), date));
            Comment savedLike = commentRepository.save(comment.get());
            return new LikeCommentResponse(savedLike.getId(), command.getUsername(), date);
        } else {
            //TODO throw correct exception
            throw new Exception();
        }
    }

    @Override
    public DislikeCommentResponse dislikeComment(DislikeCommentCommand command) {
        return null;
    }

    @Override
    public CommentQueryResult getComment(String commentId) {
        return null;
    }

    @Override
    public List<CommentQueryResult> getUserComments(String username) {
        return null;
    }

    @Override
    public List<CommentQueryResult> getPostComments(String commentId) {
        return null;
    }

    @Override
    public List<CommentQueryResult> getCommentsLikedByUser(String username) {
        return null;
    }
}
