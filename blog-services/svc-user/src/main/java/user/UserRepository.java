package user;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserRepository {

    public static Map<String, UserDto> USERS;

    static {
        USERS = new HashMap<>();
        USERS.put("user1", new UserDto("user1", "pass1"));
        USERS.put("user2", new UserDto("user2", "pass2"));
    }

}
