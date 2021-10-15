package pl.mg.blog.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

    private String message;

    private List<String> details;

}
