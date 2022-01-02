package pl.mg.blog.user.core.model.event;

import lombok.Value;

import java.io.Serializable;

@Value
public class UserRegisteredEvent implements Serializable {

    String username;
    String password;

}
