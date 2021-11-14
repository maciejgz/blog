package pl.mg.blog.simulation.events;

import pl.mg.blog.user.UserDto;
import pl.mg.blog.user.UserRepository;

import java.util.ArrayList;
import java.util.Random;

public interface SimulationEventExecutor {

    String BASE_API_URL = "http://localhost:8082/api/v1/";
    String ADD_POST_API_URL = "post";
    String RANDOM_POST_API_URL = "post/random";
    String ADD_COMMENT_API_URL = "comment";
    String RANDOM_COMMENT_API_URL = "comment";
    String DISLIKE_COMMENT_API_URL = "comment/dislike";
    String LIKE_COMMENT_API_URL = "comment/like";

    default UserDto drawUser() {
        Random rand = new Random();
        ArrayList<UserDto> userDtos = new ArrayList<>(UserRepository.USERS.values());
        return userDtos.get(rand.nextInt(userDtos.size()));
    }

    void execute();

}
