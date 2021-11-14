package pl.mg.blog.config;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "pl.mg.blog",
                         includeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, value = MongoRepository.class))
@EnableElasticsearchRepositories(
        basePackages = {"pl.mg.blog.comment.repository.search", "pl.mg.blog.post.repository.search"})
public class DataConfiguration {

}
