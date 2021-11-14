package pl.mg.blog.comment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
public class CommentCustomRepositoryImpl implements CommentCustomRepository{

    private final MongoOperations mongoOperations;

    public CommentCustomRepositoryImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
    @Override
    public Optional<Comment> getRandomComment() {
        SampleOperation sampleOperation = new SampleOperation(1);
        Aggregation aggregation = Aggregation.newAggregation(sampleOperation);
        AggregationResults<Comment> sampleCommentAggregate = mongoOperations.aggregate(aggregation, Comment.class, Comment.class);
        return Optional.ofNullable(sampleCommentAggregate.getUniqueMappedResult());
    }
}
