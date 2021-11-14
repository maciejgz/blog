package pl.mg.blog.post.repository.search;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import pl.mg.blog.post.Post;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Slf4j
public class PostSearchCustomRepositoryImpl implements PostSearchCustomRepository {

    private final ElasticsearchOperations operations;

    public PostSearchCustomRepositoryImpl(
            ElasticsearchOperations operations) {
        this.operations = operations;
    }

    @Override
    public Page<Post> fuzzySearch(String query, Pageable pageable) {
        QueryBuilder queryBuilder =
                QueryBuilders
                        .multiMatchQuery(query, "author", "title", "content")
                        .fuzziness(Fuzziness.AUTO);
        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(pageable)
                .withSort(SortBuilders.scoreSort())
                .build();

        SearchHits<Post> postHits = operations.search(searchQuery, Post.class);
        List<Post> postMatches = new ArrayList<>();
        postHits.forEach(searchHit -> postMatches.add(searchHit.getContent()));
        return new PageImpl<>(postMatches, pageable, postHits.getTotalHits());
    }

    @Override
    public Set<String> fetchAuthorSuggestions(String query) {
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("author", query + "*");
        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 5))
                .build();

        SearchHits<Post> searchSuggestions =
                operations.search(searchQuery,
                        Post.class);
        Set<String> suggestions = new HashSet<>();
        searchSuggestions.getSearchHits().forEach(searchHit -> suggestions.add(searchHit.getContent().getAuthor()));
        return suggestions;
    }

}
