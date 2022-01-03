package pl.mg.blog.legacy.post.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mg.blog.legacy.post.dto.PostQueryPagedResult;
import pl.mg.blog.legacy.post.dto.PostQueryResult;
import pl.mg.blog.legacy.post.dto.SearchPostCommand;
import pl.mg.blog.legacy.post.repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostQueryServiceImpl implements PostQueryService {

    private final PostRepository postRepository;

    public PostQueryServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Optional<PostQueryResult> findByID(String id) {
        return Optional.ofNullable(PostQueryResult.ofPost(postRepository.get(id).orElse(null)));
    }

    @Override
    public List<PostQueryResult> getAll() {
        return postRepository.getAll().stream().map(PostQueryResult::ofPost).collect(Collectors.toList());
    }

    @Override
    public List<PostQueryResult> findByUsername(String userId) {
        return postRepository.findByUsername(userId).stream().map(PostQueryResult::ofPost).collect(Collectors.toList());
    }

    @Override
    public Optional<PostQueryResult> findByCommentId(String commentId) {
        //TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<PostQueryResult> getRandomPost() {
        return Optional.ofNullable(PostQueryResult.ofPost(postRepository.getRandomPost()));
    }

    @Override
    public PostQueryPagedResult search(SearchPostCommand command) {
        //TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> getAuthorSuggestions(String query) {
        //TODO implement
        throw new UnsupportedOperationException();
    }
}
