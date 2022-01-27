package pl.mg.blog.user.infrastructure.adapter;

import pl.mg.blog.user.core.model.User;
import pl.mg.blog.user.core.port.outgoing.UserDatabase;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class UserInMemoryRepository implements UserDatabase {

    private static final List<User> users = new ArrayList<>();

    private Random rand;

    {
        try {
            rand = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public Optional<User> getRandomUser() {
        if (!users.isEmpty()) {
            return Optional.ofNullable(users.get(rand.nextInt(users.size())));
        } else {
            return Optional.empty();
        }
    }

}
