package org.john.javabrains.messenger.resources;

import org.john.javabrains.messenger.model.Comment;
import org.john.javabrains.messenger.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {
    private CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllCommentsForMessage(@PathParam("messageId") long messageId) {
        return commentService.getAllComments(messageId);
    }

    @POST
    public Comment addCommentForMessage(@PathParam("messageId") long messageId, Comment comment) {
        return commentService.addComment(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateCommentForMessage(@PathParam("messageId") long messageId,
                                           @PathParam("commentId") long commentId,
                                           Comment comment) {
        comment.setId(commentId);

        return commentService.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public Comment deleteCommentForMessage(@PathParam("messageId") long messageId,
                                           @PathParam("commentId") long commentId) {
        return commentService.removeCommentForMessage(messageId, commentId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getCommentForMessage(@PathParam("messageId") long messageId,
                                        @PathParam("commentId") long commentId) {
        return commentService.getComment(messageId, commentId);
    }
}
