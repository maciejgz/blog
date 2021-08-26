package pl.mg.blog.comment;

import java.util.List;

public interface CommentService {

    public CommentQueryResult addComment(AddCommentCommand command);

    public LikeCommentResponse likeComment(LikeCommentCommand command) throws Exception;

    public DislikeCommentResponse dislikeComment(DislikeCommentCommand command);

    public CommentQueryResult getComment(String commentId);

    public List<CommentQueryResult> getUserComments(String username);

    public List<CommentQueryResult> getPostComments(String commentId);

    public List<CommentQueryResult> getCommentsLikedByUser(String username);

}
