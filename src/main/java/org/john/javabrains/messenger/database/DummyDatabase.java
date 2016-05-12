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
    private static Map<String, Profile> profiles = new ConcurrentHashMap<>();

    static {
        messages.put(1L, new Message(1L, "Hello World!", "John"));
        messages.put(2L, new Message(2L, "Hello Jersey!", "John"));

        profiles.put("john", new Profile("Sprinkle", "John", "john", 1L));
    }

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }
}
