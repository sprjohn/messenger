package org.john.javabrains.messenger.resources;

import org.john.javabrains.messenger.model.Message;
import org.john.javabrains.messenger.resources.beans.MessageFilterBean;
import org.john.javabrains.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) {
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
    public Message addMessage(Message message) {
        return messageService.addMessage(message);
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
    public Message getMessage(@PathParam("messageId") long messageId) {
        return messageService.getMessage(messageId);
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }
}
