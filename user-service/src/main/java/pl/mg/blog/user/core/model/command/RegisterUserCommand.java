package pl.mg.blog.user.core.model.command;

import lombok.Value;

@Value
public class RegisterUserCommand {

    String username;
    String password;

}
