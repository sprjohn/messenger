package org.john.javabrains.messenger.service;

import org.john.javabrains.messenger.database.DummyDatabase;
import org.john.javabrains.messenger.model.Comment;
import org.john.javabrains.messenger.model.ErrorMessage;
import org.john.javabrains.messenger.model.Message;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {
    private Map<Long, Message> messages = DummyDatabase.getMessages();

    public List<Comment> getAllComments(long messageId) {
        Map<Long, Comment> comments = getCommentsForMessage(messageId);

        return new ArrayList<>(comments.values());
    }

    private Map<Long, Comment> getCommentsForMessage(long messageId) {
        Message message = messages.get(messageId);

        ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "http://www.google.com");
        Response response = Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();

        if (message == null) {
            throw new WebApplicationException(response);
        }

        Map<Long, Comment> comments = message.getComments();

        if (comments == null) {
            throw new WebApplicationException(response);
        }

        return comments;
    }

    public Comment getComment(long messageId, long commentId) {
        Map<Long, Comment> comments = getCommentsForMessage(messageId);

        return comments.get(commentId);
    }

    public Comment addComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = getCommentsForMessage(messageId);

        comment.setId(comments.size() + 1);
        comments.put(comment.getId(), comment);

        return comment;
    }

    public Comment updateComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = getCommentsForMessage(messageId);

        if (comment.getId() <= 0) {
            return null;
        }

        comments.put(comment.getId(), comment);

        return comment;
    }

    public Comment removeCommentForMessage(long messageId, long commentId) {
        Map<Long, Comment> comments = getCommentsForMessage(messageId);

        return comments.remove(commentId);
    }
}
