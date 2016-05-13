package org.john.javabrains.messenger.resources;

import org.john.javabrains.messenger.model.Message;
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
    public List<Message> getMessages(@QueryParam("year") int year) {
        if (year > 0) {
            return messageService.getMessagesForYear(year);
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
}
