package pl.mg.blog.user.infrastructure.adapter;

import pl.mg.blog.user.core.model.User;
import pl.mg.blog.user.core.port.outgoing.UserDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserInMemoryRepository implements UserDatabase {

    private static final List<User> users = new ArrayList<>();

    @Override
    public Optional<User> getUser(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    @Override
    public void save(User user) {
        if (users.stream().anyMatch(us -> us.getUsername().equals(user.getUsername()))) {
            users.removeIf(us -> us.getUsername().equals(user.getUsername()));
        }
        users.add(user);
    }
}
