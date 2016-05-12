package org.john.javabrains.messenger.service;

import org.john.javabrains.messenger.model.Message;

import java.util.Arrays;
import java.util.List;

public class MessageService {

    public List<Message> getAllMessages() {
        Message m1 = new Message(1L, "Hello World!", "John");
        Message m2 = new Message(2L, "Hello Jersey!", "John");

        return Arrays.asList(m1, m2);
    }
}
