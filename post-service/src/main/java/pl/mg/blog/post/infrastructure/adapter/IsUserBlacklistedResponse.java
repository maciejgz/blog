package pl.mg.blog.post.infrastructure.adapter;

import lombok.Value;

@Value
public class IsUserBlacklistedResponse {

    String username;
    String blacklistedUser;
    boolean blacklisted;
}
