package pl.mg.blog.comment.core.model.command;

import lombok.Value;

@Value
public class CheckUserExistenceCommand implements Command {
    String username;

}
