package pl.mg.blog.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document
@AllArgsConstructor
@Data
@org.springframework.data.elasticsearch.annotations.Document(indexName = "blog_post")
public class Post {

    @Id
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String author;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Date)
    private Instant created;

    @Field(type = FieldType.Date)
    private Instant updatedAt;

    @Field(type = FieldType.Long)
    private Long likes;

    private List<String> commentIds;

}
