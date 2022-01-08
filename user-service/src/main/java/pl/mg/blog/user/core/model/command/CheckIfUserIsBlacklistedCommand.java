package pl.mg.blog.user.core.model.command;

import lombok.Value;

@Value
public class CheckIfUserIsBlacklistedCommand {

    String username;
    String blacklistedUser;

}
