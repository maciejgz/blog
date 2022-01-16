package pl.mg.blog.comment.legacy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mg.blog.comment.legacy.exception.*;
import pl.mg.blog.comment.legacy.exception.*;
import pl.mg.blog.comment.legacy.model.*;
import pl.mg.blog.comment.legacy.model.*;
import pl.mg.blog.comment.legacy.post.client.PostClient;
import pl.mg.blog.comment.legacy.repository.Comment;
import pl.mg.blog.comment.legacy.repository.CommentRepository;
import pl.mg.blog.comment.legacy.repository.Like;
import pl.mg.blog.comment.legacy.user.client.UserClient;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/*@Service
@Slf4j*/
public class CommentServiceImpl implements CommentService {

    public static final String COMMENT_NOT_EXIST_LABEL = "Comment %s not exist";

    private final CommentRepository commentRepository;
    private final PostClient postClient;
    private final UserClient userClient;

    public CommentServiceImpl(CommentRepository commentRepository, PostClient postClient, UserClient userClient) {
        this.commentRepository = commentRepository;
        this.postClient = postClient;
        this.userClient = userClient;
    }

    @Override
    public CommentQueryResult addComment(AddCommentCommand command) throws PostNotFoundException, UserNotFoundException {
        checkIfPostExists(command.getPostId());
        checkIfUserExist(command.getUsername());
        Comment comment = new Comment(command);
        Instant created = Instant.now();
        comment.setCreated(created);
        comment.setId(UUID.randomUUID().toString());
        Comment savedComment = commentRepository.save(comment);
        //TODO push notification about comment
        return new CommentQueryResult(savedComment);
    }

    @Override
    public LikeCommentResponse likeComment(LikeCommentCommand command)
            throws CommentNotExistException, CommentAlreadyLikedException, UserNotFoundException {
        checkIfUserExist(command.getUsername());
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
            //TODO push notification about comment like
            return new LikeCommentResponse(savedLike.getId(), command.getUsername(), date);
        } else {
            throw new CommentNotExistException(String.format(COMMENT_NOT_EXIST_LABEL, command.getCommentId()));
        }
    }

    private boolean isCommentAlreadyLiked(String username, Set<Like> likes) {
        return likes.stream().anyMatch(like -> username.equalsIgnoreCase(like.getUsername()));
    }

    private boolean isCommentAlreadyDisliked(String username, Set<Like> likes) {
        return likes.stream().noneMatch(like -> username.equalsIgnoreCase(like.getUsername()));
    }

    private void checkIfPostExists(String postId) throws PostNotFoundException {
        ResponseEntity<Void> response = postClient.checkIfPostExists(postId);
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            throw new PostNotFoundException("Post " + postId + " not exists");
        }
    }

    private void checkIfUserExist(String username) throws UserNotFoundException {
        ResponseEntity<Void> response = userClient.checkIfUserExists(username);
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            throw new UserNotFoundException("User " + username + " not exists");
        }
    }

    @Override
    public DislikeCommentResponse dislikeComment(DislikeCommentCommand command)
            throws CommentNotExistException, CommentAlreadyDislikedException, UserNotFoundException {
        checkIfUserExist(command.getUsername());
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
            //TODO push notification about comment dislike
            return new DislikeCommentResponse(savedComment.getId(), command.getUsername());
        } else {
            throw new CommentNotExistException(String.format(COMMENT_NOT_EXIST_LABEL, command.getCommentId()));
        }
    }

    @Override
    //TODO add cache @Cacheable(value = CacheConfig.COMMENT_CACHE, key = "#commentId")
    public CommentQueryResult getComment(String commentId) throws CommentNotExistException {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            return new CommentQueryResult(comment.get());
        } else {
            throw new CommentNotExistException(String.format(COMMENT_NOT_EXIST_LABEL, commentId));
        }
    }

    @Override
    public CommentQueryPageResult getUserComments(GetCommentsByUserIdCommand command) throws UserNotFoundException {
        //TODO implement pagination
        /*PageRequest pageRequest = PageRequest.of(command.getPageableCommand().getPage(),
                command.getPageableCommand().getPageSize());
        pageRequest = pageRequest.withSort(command.getPageableCommand().getSortDirection(),
                command.getPageableCommand().getSortBy());
        Page<Comment> res = commentRepository.findAllByAuthor(command.getUsername(), pageRequest);
        QueryResultPage pageInfo = new QueryResultPage(res.getNumber(), res.getSize(), res.getTotalPages(),
                res.getTotalElements(), command.getPageableCommand().getSortBy(),
                command.getPageableCommand().getSortDirection().name());
        List<CommentQueryResult> result = res.get().map(CommentQueryResult::new).collect(Collectors.toList());
        return CommentQueryPageResult.builder().result(result).pageInfo(pageInfo).build();*/
        checkIfUserExist(command.getUsername());
        List<Comment> res = commentRepository.findAllByAuthor(command.getUsername());
        return new CommentQueryPageResult(res.stream().map(CommentQueryResult::new).collect(Collectors.toList()), null);
    }

    @Override
    public CommentQueryPageResult getPostComments(GetCommentsByPostIdCommand command) throws PostNotFoundException {
        //TODO pagination
      /*  PageRequest pageRequest = PageRequest.of(command.getPageableCommand().getPage(),
                command.getPageableCommand().getPageSize());
        pageRequest = pageRequest.withSort(command.getPageableCommand().getSortDirection(),
                command.getPageableCommand().getSortBy());
        Page<Comment> res = commentRepository.findAllByPostId(command.getPostId(), pageRequest);
        QueryResultPage pageInfo = new QueryResultPage(res.getNumber(), res.getSize(), res.getTotalPages(),
                res.getTotalElements(), command.getPageableCommand().getSortBy(),
                command.getPageableCommand().getSortDirection().name());
        List<CommentQueryResult> result = res.get().map(CommentQueryResult::new).collect(Collectors.toList());
        return CommentQueryPageResult.builder().result(result).pageInfo(pageInfo).build();*/
        checkIfPostExists(command.getPostId());
        List<Comment> res = commentRepository.findAllByPostId(command.getPostId());
        return new CommentQueryPageResult(res.stream().map(CommentQueryResult::new).collect(Collectors.toList()), null);
    }

    @Override
    public List<CommentQueryResult> getCommentsLikedByUser(String username) throws UserNotFoundException {
        //TODO add search criteria
        checkIfUserExist(username);
        Set<Comment> allByLikedComment = commentRepository.findAllByLikesUsername(username);
        return allByLikedComment.stream().map(CommentQueryResult::new).collect(Collectors.toList());
    }

    @Override
    public Optional<CommentQueryResult> getRandomComment() {
        return Optional.of(new CommentQueryResult(commentRepository.getRandomComment()));
    }
}
