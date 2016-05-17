package org.john.javabrains.messenger.database;

import org.john.javabrains.messenger.model.Comment;
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
        Message m1 = new Message(1L, "Hello World!", "john");
        Comment comment = new Comment(1L, "Nice first message!", "john");
        m1.getComments().put(1L, comment);

        messages.put(1L, m1);
        messages.put(2L, new Message(2L, "Hello Jersey!", "john"));

        profiles.put("john", new Profile("Sprinkle", "John", "john", 1L));
    }

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }
}
