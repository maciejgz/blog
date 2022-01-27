package pl.mg.blog.simulator.commons;

import lombok.Value;

@Value
public class RegisterUserCommand {

    String username;
    String password;

}
