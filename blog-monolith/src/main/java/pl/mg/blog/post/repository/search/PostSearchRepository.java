package pl.mg.blog.post.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pl.mg.blog.post.Post;

@Repository
public interface PostSearchRepository extends ElasticsearchRepository<Post, String>, PostSearchCustomRepository {

}
