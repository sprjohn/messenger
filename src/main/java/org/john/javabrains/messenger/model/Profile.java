package org.john.javabrains.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Profile {
    private long id;
    private String profileName;
    private String firstName;
    private String lastName;
    private Date created;

    /**
     * XML/JSON support
     */
    public Profile() {}

    public Profile(String lastName, String firstName, String profileName, long id) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.profileName = profileName;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
