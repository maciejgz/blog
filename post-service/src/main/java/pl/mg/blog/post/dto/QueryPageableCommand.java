package pl.mg.blog.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryPageableCommand {
    private int page;
    private int pageSize;
    private String sortBy;
    private String sortDirection;
}
