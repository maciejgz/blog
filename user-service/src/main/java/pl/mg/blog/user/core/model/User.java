package pl.mg.blog.user.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import pl.mg.blog.user.core.model.exception.UserAlreadyBlacklistedException;
import pl.mg.blog.user.core.model.exception.UserNotBlacklistedException;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
public class User {

    private String username;
    private String password;
    private List<BlacklistedUser> blacklistedUsers;

    public User(String username, String password, List<BlacklistedUser> blacklist) {
        this.username = username;
        this.password = password;
        this.blacklistedUsers = blacklist;
    }

    public void blacklistUser(String username) throws UserAlreadyBlacklistedException {
        if (blacklistedUsers != null && blacklistedUsers.stream().anyMatch(user -> user.getUsername().equals(username))) {
            throw new UserAlreadyBlacklistedException(username);
        }
        if (this.blacklistedUsers == null) {
            this.blacklistedUsers = new ArrayList<>();
        }
        this.blacklistedUsers.add(new BlacklistedUser(username));
    }

    public void removeUserFromBlacklist(String username) throws UserNotBlacklistedException {
        if (blacklistedUsers == null || blacklistedUsers.stream().noneMatch(user -> user.getUsername().equals(username))) {
            throw new UserNotBlacklistedException(username);
        }
        this.blacklistedUsers.remove(new BlacklistedUser(username));
    }
}
