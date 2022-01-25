package pl.mg.blog.user.core.model.command;

import lombok.Value;

@Value
public class RemoveUserFromBlacklistCommand {

    String user;
    String blacklistedUser;

}
