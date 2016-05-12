package org.john.javabrains.messenger.service;

import org.john.javabrains.messenger.database.DummyDatabase;
import org.john.javabrains.messenger.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageService {
    private Map<Long, Message> messages = DummyDatabase.getMessages();

    public MessageService() {
        messages.put(1L, new Message(1L, "Hello World!", "John"));
        messages.put(2L, new Message(2L, "Hello Jersey!", "John"));
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public Message getMessage(long id) {
        return messages.get(id);
    }

    public synchronized Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);

        return message;
    }

    public Message updateMessage(Message message) {
        if (messages.get(message.getId()) == null) {
            return null;
        }

        messages.put(message.getId(), message);

        return message;
    }

    public Message removeMessage(long id) {
        return messages.remove(id);
    }
}
