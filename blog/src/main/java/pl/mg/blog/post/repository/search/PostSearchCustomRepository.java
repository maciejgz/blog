package pl.mg.blog.post.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.mg.blog.post.Post;

import java.util.Set;

public interface PostSearchCustomRepository {

    Page<Post> fuzzySearch(String query, Pageable pageable);

    Set<String> fetchAuthorSuggestions(String query);
}
