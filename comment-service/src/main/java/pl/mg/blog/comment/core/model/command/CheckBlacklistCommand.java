package pl.mg.blog.comment.core.model.command;

import lombok.Value;

@Value
public class CheckBlacklistCommand implements Command {

    String username;
    String blacklistedUser;

}
