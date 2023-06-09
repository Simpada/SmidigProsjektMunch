package no.kristiania.collectthemunch.entities;

import java.util.Arrays;
import java.util.List;

public class User {

    private int userId;
    private String username;
    private String password;
    private String dateOfBirth;
    private String email;
    private List<String> preferences;
    private byte[] profilePicture;  //temp datatype until we know how to parse
    private int currentPoints;
    private int weeklyPoints;
    private int monthlyPoints;
    private int allTimePoints;

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
                "Profile picture: " + Arrays.toString(this.profilePicture);
    }

    public void printPreferences() {
        System.out.println("Preferences: ");

        for (String s : this.preferences) {
            System.out.println(s + " ");
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

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getWeeklyPoints() {
        return weeklyPoints;
    }

    public void setWeeklyPoints(int weeklyPoints) {
        this.weeklyPoints = weeklyPoints;
    }

    public int getMonthlyPoints() {
        return monthlyPoints;
    }

    public void setMonthlyPoints(int monthlyPoints) {
        this.monthlyPoints = monthlyPoints;
    }

    public int getAllTimePoints() {
        return allTimePoints;
    }

    public void setAllTimePoints(int allTimePoints) {
        this.allTimePoints = allTimePoints;
    }


}
