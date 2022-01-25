package pl.mg.blog.comment.core.port.outgoing;

import pl.mg.blog.comment.core.model.command.GetPostAuthorCommand;
import pl.mg.blog.comment.core.model.command.PostAuthorResponse;
import pl.mg.blog.comment.core.model.exception.PostNotFoundException;

public interface GetPostAuthor {

    PostAuthorResponse getPostAuthor(GetPostAuthorCommand command) throws PostNotFoundException;

}
