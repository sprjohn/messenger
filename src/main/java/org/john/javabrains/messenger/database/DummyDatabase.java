package org.john.javabrains.messenger.database;

import org.john.javabrains.messenger.model.Message;
import org.john.javabrains.messenger.model.Profile;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Dummy database class
 */
public class DummyDatabase {
    private static Map<Long, Message> messages = new ConcurrentHashMap<>();
    private static Map<Long, Profile> profiles = new ConcurrentHashMap<>();

    static {
        messages.put(1L, new Message(1L, "Hello World!", "John"));
        messages.put(2L, new Message(2L, "Hello Jersey!", "John"));
    }

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<Long, Profile> getProfiles() {
        return profiles;
    }
}
