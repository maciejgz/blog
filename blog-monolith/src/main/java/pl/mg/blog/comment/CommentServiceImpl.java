package pl.mg.blog.comment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mg.blog.comment.exception.CommentAlreadyDislikedException;
import pl.mg.blog.comment.exception.CommentAlreadyLikedException;
import pl.mg.blog.comment.exception.CommentNotExistException;
import pl.mg.blog.comment.repository.search.CommentSearchRepository;
import pl.mg.blog.commons.QueryResultPage;
import pl.mg.blog.config.CacheConfig;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    public static final String COMMENT_NOT_EXIST_LABEL = "Comment %s not exist";
    private final CommentRepository commentRepository;
    private final CommentSearchRepository commentSearchRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
            CommentSearchRepository commentSearchRepository) {
        this.commentRepository = commentRepository;
        this.commentSearchRepository = commentSearchRepository;
    }

    @Override
    @Transactional
    public CommentQueryResult addComment(AddCommentCommand command) {
        //TODO business validation
        //TODO verify post existence in post microservice using HEAD request: HEAD  post/{postId}.
        // User is verified in controller based on the token
        Comment comment = new Comment(command);
        Instant created = Instant.now();
        comment.setCreated(created);
        Comment savedComment = commentRepository.save(comment);
        commentSearchRepository.save(comment);
        //TODO push notification about comment
        return new CommentQueryResult(savedComment);
    }

    @Override
    @Transactional
    public LikeCommentResponse likeComment(LikeCommentCommand command)
            throws CommentNotExistException, CommentAlreadyLikedException {
        //TODO business validation
        Optional<Comment> comment = commentRepository.findById(command.getCommentId());
        if (comment.isPresent()) {
            if (comment.get().getLikes() == null) {
                comment.get().setLikes(new HashSet<>());
            }
            if (isCommentAlreadyLiked(command.getUsername(), comment.get().getLikes())) {
                throw new CommentAlreadyLikedException(
                        String.format("Comment already liked %s", command.getCommentId()));
            }
            Instant date = Instant.now();
            comment.get().getLikes().add(new Like(command.getUsername(), date));
            Comment savedLike = commentRepository.save(comment.get());
            commentSearchRepository.save(comment.get());
            //TODO push notification about comment like
            return new LikeCommentResponse(savedLike.getId(), command.getUsername(), date);
        } else {
            throw new CommentNotExistException(String.format(COMMENT_NOT_EXIST_LABEL, command.getCommentId()));
        }
    }

    @Override
    @Transactional
    public DislikeCommentResponse dislikeComment(DislikeCommentCommand command)
            throws CommentNotExistException, CommentAlreadyDislikedException {
        //TODO business validation
        Optional<Comment> comment = commentRepository.findById(command.getCommentId());
        if (comment.isPresent()) {
            if (comment.get().getLikes() == null) {
                comment.get().setLikes(new HashSet<>());
            }
            if (isCommentAlreadyDisliked(command.getUsername(), comment.get().getLikes())) {
                throw new CommentAlreadyDislikedException(
                        String.format("Comment already disliked %s", command.getCommentId()));
            }
            comment.get().getLikes().removeIf(like -> command.getUsername().equalsIgnoreCase(like.getUsername()));
            Comment savedComment = commentRepository.save(comment.get());
            commentSearchRepository.save(comment.get());
            //TODO push notification about comment dislike
            return new DislikeCommentResponse(savedComment.getId(), command.getUsername());
        } else {
            throw new CommentNotExistException(String.format(COMMENT_NOT_EXIST_LABEL, command.getCommentId()));
        }
    }

    @Override
    @Cacheable(value = CacheConfig.COMMENT_CACHE, key = "#commentId")
    public CommentQueryResult getComment(String commentId) throws CommentNotExistException {
        //TODO business validation
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            return new CommentQueryResult(comment.get());
        } else {
            throw new CommentNotExistException(String.format(COMMENT_NOT_EXIST_LABEL, commentId));
        }
    }

    @Override
    public CommentQueryPageResult getUserComments(GetCommentsByUserIdCommand command) {
        //TODO business validation
        //TODO verify user existence in user microservice
        PageRequest pageRequest = PageRequest.of(command.getPageableCommand().getPage(),
                command.getPageableCommand().getPageSize());
        pageRequest = pageRequest.withSort(command.getPageableCommand().getSortDirection(),
                command.getPageableCommand().getSortBy());
        Page<Comment> res = commentRepository.findAllByAuthor(command.getUsername(), pageRequest);
        QueryResultPage pageInfo = new QueryResultPage(res.getNumber(), res.getSize(), res.getTotalPages(),
                res.getTotalElements(), command.getPageableCommand().getSortBy(),
                command.getPageableCommand().getSortDirection().name());
        List<CommentQueryResult> result = res.get().map(CommentQueryResult::new).collect(Collectors.toList());
        return CommentQueryPageResult.builder().result(result).pageInfo(pageInfo).build();
    }

    @Override
    public CommentQueryPageResult getPostComments(GetCommentsByPostIdCommand command) {
        //TODO business validation
        //TODO verify post existence in post microservice
        PageRequest pageRequest = PageRequest.of(command.getPageableCommand().getPage(),
                command.getPageableCommand().getPageSize());
        pageRequest = pageRequest.withSort(command.getPageableCommand().getSortDirection(),
                command.getPageableCommand().getSortBy());
        Page<Comment> res = commentRepository.findAllByPostId(command.getPostId(), pageRequest);
        QueryResultPage pageInfo = new QueryResultPage(res.getNumber(), res.getSize(), res.getTotalPages(),
                res.getTotalElements(), command.getPageableCommand().getSortBy(),
                command.getPageableCommand().getSortDirection().name());
        List<CommentQueryResult> result = res.get().map(CommentQueryResult::new).collect(Collectors.toList());
        return CommentQueryPageResult.builder().result(result).pageInfo(pageInfo).build();
    }

    @Override
    public List<CommentQueryResult> getCommentsLikedByUser(String username) {
        //TODO business validation
        //TODO verify user existence in user microservice
        //TODO add search criteria
        Set<Comment> allByLikedComment = commentRepository.findAllByLikesUsername(username);
        return allByLikedComment.stream().map(CommentQueryResult::new).collect(Collectors.toList());
    }

    @Override
    public Optional<CommentQueryResult> getRandomPost() {
        return commentRepository.getRandomComment().map(CommentQueryResult::new);
    }

    private boolean isCommentAlreadyLiked(String username, Set<Like> likes) {
        return likes.stream().anyMatch(like -> username.equalsIgnoreCase(like.getUsername()));
    }

    private boolean isCommentAlreadyDisliked(String username, Set<Like> likes) {
        return likes.stream().noneMatch(like -> username.equalsIgnoreCase(like.getUsername()));
    }
}
