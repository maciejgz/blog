package pl.mg.blog.post;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.mg.blog.commons.QueryResultPage;
import pl.mg.blog.post.repository.search.PostSearchRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Service
@Slf4j
public class PostQueryService {

    private final PostRepository repository;
    private final PostSearchRepository postSearchRepository;

    public PostQueryService(PostRepository repository,
            PostSearchRepository postSearchRepository) {
        this.repository = repository;
        this.postSearchRepository = postSearchRepository;
    }

    public Optional<PostQueryResult> findByID(@Valid @NotEmpty String id) {
        Optional<Post> post = this.repository.findById(id);
        return post.map(PostQueryResult::ofPost);
    }

    public List<PostQueryResult> findByUsername(@Valid @NotEmpty String userId) {
        List<Post> allByAuthor = repository.findAllByAuthor(userId);
        return allByAuthor.stream().map(PostQueryResult::ofPost).collect(Collectors.toList());
    }

    public Optional<PostQueryResult> findByCommentId(@Valid @NotEmpty String commentId) {
        Optional<Post> byComment = repository.findFirstByCommentIdsContains(commentId);
        return byComment.map(PostQueryResult::ofPost);
    }

    public Optional<PostQueryResult> getRandomPost() {
        return repository.getRandomPost().map(PostQueryResult::ofPost);
    }

    public PostQueryPagedResult search(SearchPostCommand command) {
        PageRequest pageRequest = PageRequest.of(command.getPageableCommand().getPage(),
                command.getPageableCommand().getPageSize());
        if (StringUtils.isNotBlank(command.getPageableCommand().getSortBy())) {
            pageRequest = pageRequest.withSort(command.getPageableCommand().getSortDirection(),
                    command.getPageableCommand().getSortBy());
        }
        Page<Post> posts = postSearchRepository.fuzzySearch(command.getQuery(), pageRequest);
        PostQueryPagedResult result = new PostQueryPagedResult();
        result.setResults(posts.stream().map(PostQueryResult::ofPost).collect(Collectors.toList()));
        result.setPage(
                new QueryResultPage(posts.getNumber(), posts.getSize(), posts.getTotalPages(), posts.getTotalElements(),
                        command.getPageableCommand().getSortBy(),
                        command.getPageableCommand().getSortDirection().name()));
        return result;
    }

    public Set<String> getAuthorSuggestions(String query) {
        return postSearchRepository.fetchAuthorSuggestions(query);
    }
}
