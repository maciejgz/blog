package pl.mg.blog.comment.core.model.command;

import lombok.Value;
import pl.mg.blog.comment.core.model.command.Command;

@Value
public class CheckUserExistenceResult {
    boolean userExists;

}
