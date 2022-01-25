package pl.mg.blog.comment.core.port.outgoing;

import pl.mg.blog.comment.core.model.command.CheckPostExistenceCommand;
import pl.mg.blog.comment.core.model.command.CheckPostExistenceResponse;

public interface CheckPostExistence {

    CheckPostExistenceResponse checkPostExistence(CheckPostExistenceCommand command);

}
