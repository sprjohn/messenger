package org.john.javabrains.messenger.service;

import org.john.javabrains.messenger.database.DummyDatabase;
import org.john.javabrains.messenger.model.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageService {
    private Map<Long, Message> messages = DummyDatabase.getMessages();

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public List<Message> getMessagesForYear(int year) {
        return messages.values().stream()
                .filter(m -> m.getCreated().getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Message> getAllMessagePaginated(int start, int size) {
        List<Message> list = new ArrayList<>(messages.values());

        if (start + size > list.size()) {
            return Collections.emptyList();
        }

        return list.subList(start, start + size);
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
