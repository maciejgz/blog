package pl.mg.blog.user.core.model.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

@Value
@EqualsAndHashCode(callSuper = true)
public class UserRegisteredEvent extends Event implements Serializable {

    String username;
    String password;

}
