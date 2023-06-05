package no.kristiania.collectthemunch.entities;

import java.util.List;

public class User {

    private int userId;
    private String username;
    private String password;
    private String dateOfBirth;
    private String email;
    private List<Category> preferences;
    private String profilePicture = "an image";  //temp datatype until we know how to parse

    public User() {

    }

    public User(int userId, String userName, String password, String dateOfBirth, String email) {
        this.userId = userId;
        this.username = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + this.userId + "\n" +
                "Name: " + this.username + "\n" +
                "password: " + this.password + "\n" +
                "Date of birth: " + this.dateOfBirth + "\n" +
                "Mail: " + this.email + "\n" +
                "Profile picture: " + this.profilePicture;
    }

    public void printPreferences() {
        System.out.println("Preferences: ");

        for (Category c : this.preferences) {
            System.out.println(c + " ");
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<Category> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Category> preferences) {
        this.preferences = preferences;
    }


    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
