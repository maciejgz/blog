package pl.mg.blog.simulator.events;

import pl.mg.blog.simulator.commons.UserDto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public interface SimulationEventExecutor {

    //TODO rewrite with declarative http client
    String BASE_API_URL = "http://localhost:8081/";
    String ADD_POST_API_URL = "post-service/post";
    String RANDOM_POST_API_URL = "post-service/post/random";
    String ADD_COMMENT_API_URL = "comment-service/comment";
    String RANDOM_COMMENT_API_URL = "comment-service/comment";
    String DISLIKE_COMMENT_API_URL = "comment-service/comment/dislike";
    String LIKE_COMMENT_API_URL = "comment-service/comment/like";
    String BLACKLIST_USER_API_URL = "user-service/user/blacklist";
    String REMOVE_USER_FROM_BLACKLIST_API_URL = "user-service/user/blacklist";
    String REGISTER_USER_API_URL = "user-service/user";

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
