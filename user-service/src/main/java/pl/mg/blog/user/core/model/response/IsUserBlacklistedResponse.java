package pl.mg.blog.user.core.model.response;

import lombok.Value;

@Value
public class IsUserBlacklistedResponse {

    String username;
    String blacklistedUser;
    boolean blacklisted;
}
