package org.john.javabrains.messenger.resources;

import org.john.javabrains.messenger.model.Message;
import org.john.javabrains.messenger.resources.beans.MessageFilterBean;
import org.john.javabrains.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getJsonMessages(@BeanParam MessageFilterBean messageFilterBean) {
        System.out.println("JSON method called");

        final int year = messageFilterBean.getYear();
        final int start = messageFilterBean.getStart();
        final int size = messageFilterBean.getSize();

        if (year > 0) {
            return messageService.getMessagesForYear(year);
        }
        if (start >= 0 && size > 0) {
            return messageService.getAllMessagePaginated(start, size);
        }

        return messageService.getAllMessages();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Message> getXMLMessages(@BeanParam MessageFilterBean messageFilterBean) {
        System.out.println("XML method called");

        final int year = messageFilterBean.getYear();
        final int start = messageFilterBean.getStart();
        final int size = messageFilterBean.getSize();

        if (year > 0) {
            return messageService.getMessagesForYear(year);
        }
        if (start >= 0 && size > 0) {
            return messageService.getAllMessagePaginated(start, size);
        }

        return messageService.getAllMessages();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addJSONMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
        Message addedMessage = messageService.addMessage(message);

        String newMessageId = String.valueOf(addedMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newMessageId).build();

        return Response.created(uri)
                .entity(addedMessage)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response addXMLMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
        Message addedMessage = messageService.addMessage(message);

        String newMessageId = String.valueOf(addedMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newMessageId).build();

        return Response.created(uri)
                .entity(addedMessage)
                .build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long messageId) {
        messageService.removeMessage(messageId);
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
        Message message = messageService.getMessage(messageId);

        String messageUri = getUriForSelf(message, uriInfo);
        String profileUri = getUriForProfile(message, uriInfo);
        String commentsUri = getUriForComments(message, uriInfo);

        message.addLink(messageUri, "self");
        message.addLink(profileUri, "author");
        message.addLink(commentsUri, "comments");

        return message;
    }

    private String getUriForComments(Message message, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentResource")
                .path(CommentResource.class)
                .resolveTemplate("messageId", message.getId())
                .build()
                .toString();
    }

    private String getUriForProfile(Message message, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build()
                .toString();
    }

    private String getUriForSelf(Message message, @Context UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(String.valueOf(message.getId()))
                .build()
                .toString();
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }
}
