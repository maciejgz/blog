package pl.mg.blog.comment.core.port.outgoing;

import pl.mg.blog.comment.core.model.command.CheckUserExistenceCommand;
import pl.mg.blog.comment.core.model.command.CheckUserExistenceResult;

public interface CheckUserExistence {

    CheckUserExistenceResult checkUserExistence(CheckUserExistenceCommand command);
}
