package pl.mg.blog.comment.legacy.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private String username;

    private String password;
}
