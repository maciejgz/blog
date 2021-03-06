package pl.mg.blog.user.core.port.incoming;


import pl.mg.blog.user.core.model.User;
import pl.mg.blog.user.core.model.command.RegisterUserCommand;
import pl.mg.blog.user.core.model.exception.UserRegistrationException;

/**
 * Incoming port for user registration
 */
public interface RegisterUser {

    User registerUser(RegisterUserCommand command) throws UserRegistrationException;


}
