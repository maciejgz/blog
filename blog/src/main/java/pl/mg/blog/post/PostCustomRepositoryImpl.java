package pl.mg.blog.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;

    public PostCustomRepositoryImpl(MongoTemplate mongoTemplate,
            MongoOperations mongoOperations) {
        this.mongoTemplate = mongoTemplate;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public Optional<Post> getRandomPost() {
        log.debug("mongo: {}", mongoTemplate != null);
        SampleOperation sampleOperation = new SampleOperation(1);
        Aggregation aggregation = Aggregation.newAggregation(sampleOperation);
        AggregationResults<Post> samplePostAggregate = mongoOperations.aggregate(aggregation, Post.class, Post.class);
        return Optional.ofNullable(samplePostAggregate.getUniqueMappedResult());
    }
}
