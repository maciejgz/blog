package pl.mg.blog.user.core.port.outgoing;

import pl.mg.blog.user.core.model.User;

import javax.naming.directory.InvalidAttributesException;
import java.util.Optional;

/**
 * Outgoing port to the database.
 */
public interface UserDatabase {

    Optional<User> getUser(String username);

    void save(User user);
}
