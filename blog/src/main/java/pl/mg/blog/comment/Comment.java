package pl.mg.blog.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@org.springframework.data.elasticsearch.annotations.Document(indexName = "blog_comment")
public class Comment {

    @Id
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Text)
    private String author;

    @Field(type = FieldType.Text)
    private String postId;

    @Field(type = FieldType.Date)
    private Instant created;

    private Set<Like> likes;

    public Comment(AddCommentCommand command) {
        this.author = command.getUsername();
        this.content = command.getContent();
        this.postId = command.getPostId();
    }

}
