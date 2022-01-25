package pl.mg.blog.user.core.port.incoming;

import pl.mg.blog.user.core.model.User;
import pl.mg.blog.user.core.model.exception.UserNotFoundException;

public interface GetUser {

    User getUser(String username) throws UserNotFoundException;

}
