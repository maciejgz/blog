package pl.mg.blog.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
public class QueryPageableCommand {
    private int page;
    private int pageSize;
    private String sortBy;
    private Sort.Direction sortDirection;
}
