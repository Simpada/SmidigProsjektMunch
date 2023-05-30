package no.kristiania.collectthemunch.entities;

import java.util.List;
import java.util.Locale;

public class User {

    private int id;
    private String userName;
    private String password;
    private String dateOfBirth;
    private String email;
    private List<Locale.Category> preferences;
    private String profilePicture;  //temp datatype until we know how to parse


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Locale.Category> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Locale.Category> preferences) {
        this.preferences = preferences;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
