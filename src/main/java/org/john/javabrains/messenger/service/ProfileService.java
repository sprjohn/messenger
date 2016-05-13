package org.john.javabrains.messenger.service;

import org.john.javabrains.messenger.database.DummyDatabase;
import org.john.javabrains.messenger.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {
    private Map<String, Profile> profiles = DummyDatabase.getProfiles();

    public List<Profile> getAllProfiles() {
        return new ArrayList<>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        if (profiles.get(profile.getProfileName()) != null) {
            return null;
        }

        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);

        return profile;
    }

    public Profile updateProfile(Profile profile) {
        Profile existingProfile = profiles.get(profile.getProfileName());

        if (existingProfile == null) {
            return null;
        }

        profile.setId(existingProfile.getId());
        profiles.put(profile.getProfileName(), profile);

        return profile;
    }

    public Profile removeProfile(String profileName) {
        return profiles.remove(profileName);
    }
}
