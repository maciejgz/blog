package pl.mg.blog.user.core.model.command;

import lombok.Value;

@Value
public class BlacklistUserCommand {

    String user;
    String blacklistedUser;

}
