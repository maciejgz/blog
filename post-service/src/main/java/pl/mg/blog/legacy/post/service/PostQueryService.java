package pl.mg.blog.legacy.post.service;

import org.springframework.cache.annotation.Cacheable;
import pl.mg.blog.legacy.post.CacheConfig;
import pl.mg.blog.legacy.post.dto.PostQueryPagedResult;
import pl.mg.blog.legacy.post.dto.PostQueryResult;
import pl.mg.blog.legacy.post.dto.SearchPostCommand;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostQueryService {

    @Cacheable(value = CacheConfig.POST_CACHE, key = "#id")
    public Optional<PostQueryResult> findByID(@Valid @NotEmpty String id);

    List<PostQueryResult> getAll();

    public List<PostQueryResult> findByUsername(@Valid @NotEmpty String userId);

    @Cacheable(value = CacheConfig.POST_CACHE, key = "'comment' + #commentId")
    public Optional<PostQueryResult> findByCommentId(@Valid @NotEmpty String commentId);

    public Optional<PostQueryResult> getRandomPost();

    public PostQueryPagedResult search(SearchPostCommand command);

    public Set<String> getAuthorSuggestions(String query);
}
