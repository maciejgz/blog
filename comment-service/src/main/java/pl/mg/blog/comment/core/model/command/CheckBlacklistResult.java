package pl.mg.blog.comment.core.model.command;

import lombok.Value;

@Value
public class CheckBlacklistResult {

    String username;
    String blacklistedUser;
    boolean blacklisted;

}
