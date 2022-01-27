package pl.mg.blog.simulator.commons;

import lombok.Value;

@Value
public class RemoveUserFromBlacklistCommand {

    String user;
    String blacklistedUser;

}
