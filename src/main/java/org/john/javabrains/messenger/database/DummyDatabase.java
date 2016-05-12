package org.john.javabrains.messenger.database;

import org.john.javabrains.messenger.model.Message;
import org.john.javabrains.messenger.model.Profile;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DummyDatabase {
    private static Map<Long, Message> messages = new ConcurrentHashMap<>();
    private static Map<Long, Profile> profiles = new ConcurrentHashMap<>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<Long, Profile> getProfiles() {
        return profiles;
    }
}
