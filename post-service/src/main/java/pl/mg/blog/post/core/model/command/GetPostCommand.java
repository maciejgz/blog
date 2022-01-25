package pl.mg.blog.post.core.model.command;

import lombok.AllArgsConstructor;
import lombok.Value;
import pl.mg.blog.commons.Command;

import javax.validation.constraints.NotNull;

@Value
@AllArgsConstructor
public class GetPostCommand implements Command {

    @NotNull String id;

}
