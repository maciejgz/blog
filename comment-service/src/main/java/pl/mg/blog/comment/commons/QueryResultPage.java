package pl.mg.blog.comment.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryResultPage {

    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private String sortBy;
    private String sortDirection;
}
