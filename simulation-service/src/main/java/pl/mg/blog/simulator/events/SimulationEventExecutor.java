package pl.mg.blog.simulator.events;

import pl.mg.blog.simulator.commons.UserDto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public interface SimulationEventExecutor {

    //TODO rewrite with declarative http client
    String BASE_API_URL = "http://localhost:8082/api/v1/";
    String ADD_POST_API_URL = "post";
    String RANDOM_POST_API_URL = "post/random";
    String ADD_COMMENT_API_URL = "comment";
    String RANDOM_COMMENT_API_URL = "comment";
    String DISLIKE_COMMENT_API_URL = "comment/dislike";
    String LIKE_COMMENT_API_URL = "comment/like";

    default UserDto drawUser() {
        Random rand;
        try {
            rand = SecureRandom.getInstanceStrong();
            //FIXME get users from user service using declarative HTTP client
            //ArrayList<UserDto> userDtos = new ArrayList<>(UserRepository.USERS.values());
            ArrayList<UserDto> userDtos = new ArrayList<>();
            return userDtos.get(rand.nextInt(userDtos.size()));
        } catch (NoSuchAlgorithmException e) {
            //TODO throw custom exception
            return null;
        }
    }

    void execute();

}
