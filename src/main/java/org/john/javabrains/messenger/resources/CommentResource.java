package org.john.javabrains.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class CommentResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "New subresource";
    }

    @GET
    @Path("/{commentId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCommentForMessage(@PathParam("messageId") int messageId, @PathParam("commentId") int commentId) {
        return "Comment #" + commentId + " for message #" + messageId;
    }
}
