package org.john.javabrains.messenger.service;

import org.john.javabrains.messenger.database.DummyDatabase;
import org.john.javabrains.messenger.model.Comment;
import org.john.javabrains.messenger.model.Message;

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
        return messages.get(messageId).getComments();
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
