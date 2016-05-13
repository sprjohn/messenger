package org.john.javabrains.messenger.service;

import org.john.javabrains.messenger.database.DummyDatabase;
import org.john.javabrains.messenger.exception.DataNotFoundException;
import org.john.javabrains.messenger.model.Message;

import java.util.*;

public class MessageService {
    private Map<Long, Message> messages = DummyDatabase.getMessages();

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public List<Message> getMessagesForYear(int year) {
        List<Message> messagesForYear = new ArrayList<>();

        Calendar cal = Calendar.getInstance();

        for (Message message : messages.values()) {
            cal.setTime(message.getCreated());

            if (cal.get(Calendar.YEAR) == year) {
                messagesForYear.add(message);
            }
        }

        return messagesForYear;
    }

    public List<Message> getAllMessagePaginated(int start, int size) {
        List<Message> list = new ArrayList<>(messages.values());

        if (start + size > list.size()) {
            return Collections.emptyList();
        }

        return list.subList(start, start + size);
    }

    public Message getMessage(long id) {
        Message message = messages.get(id);

        if (message == null) {
            throw new DataNotFoundException("Message with id " + id + " not found");
        }

        return message;
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
