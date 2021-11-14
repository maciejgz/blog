package pl.mg.blog.comment.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pl.mg.blog.comment.Comment;

@Repository
public interface CommentSearchRepository extends ElasticsearchRepository<Comment, String> {

}
