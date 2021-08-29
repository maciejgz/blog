package pl.mg.blog.simulation.events;

import pl.mg.blog.user.UserDto;
import pl.mg.blog.user.UserRepository;

import java.util.ArrayList;
import java.util.Random;

public interface SimulationEventExecutor {

    default UserDto drawUser() {
        Random rand = new Random();
        ArrayList<UserDto> userDtos = new ArrayList<>(UserRepository.USERS.values());
        return userDtos.get(rand.nextInt(userDtos.size()));
    }

    void execute();

}
