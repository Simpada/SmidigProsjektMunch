package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import no.kristiania.collectthemunch.entities.Category;
import no.kristiania.collectthemunch.entities.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static no.kristiania.collectthemunch.entities.Category.*;

public class UserDao extends AbstractDao {

    @Inject
    public UserDao(DataSource dataSource) {
        super(dataSource);
    }

    public void save(User user, ArrayList<String> preferences) throws SQLException {
        saveUser(user);
        saveUserPreferences(user, preferences);
    }

    private void saveUser(User user) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "INSERT INTO Users (username, password, date_of_birth, email, profile_picture) VALUES (?, ?, ?, ?, ?)";

            try (var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getDateOfBirth());
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getProfilePicture());

                statement.executeUpdate();
                try (var generatedKeys = statement.getGeneratedKeys()) {
                    generatedKeys.next();
                    user.setUserId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void saveUserPreferences(User user, ArrayList<String> preferences) throws SQLException {
        user.setPreferences(parseCategory(preferences));

        if (user.getPreferences() == null || user.getPreferences().size() == 0) {
            return;
        }

        try (var connection = dataSource.getConnection()) {
            String query = "INSERT INTO Preferences (user_id, preferences) VALUES ?, ?";

            for (Category c : user.getPreferences()) {
                try (var statement = connection.prepareStatement(query)) {
                    statement.setInt(1, user.getUserId());
                    statement.setString(2, String.valueOf(c));
                }
            }
        }
    }

    //Parse Category as string from frontend to Category enums.
    private static ArrayList<Category> parseCategory(ArrayList<String> preferences) {
        preferences.replaceAll(String::toUpperCase);


        ArrayList<Category> convertedPreferences = new ArrayList<>();
        for (String s : preferences) {
            switch (s) {
                case "PARTY" -> convertedPreferences.add(PARTY);
                case "EXHIBITION" -> convertedPreferences.add(EXHIBITION);
                case "KIDS" -> convertedPreferences.add(KIDS);
                case "FAMILY" -> convertedPreferences.add(FAMILY);
                case "NEW" -> convertedPreferences.add(NEW);
                case "GAMES" -> convertedPreferences.add(GAMES);
            }
        }

        if (preferences.size() == convertedPreferences.size()) {
            return convertedPreferences;
        }
        return null;
    }


    public User retrieve(int userId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Users WHERE user_id = ?";

            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);

                try (var resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        var user = new User();

                        user = mapFromResultSet(resultSet);
                        user.setPreferences(retrieveUserPreferences(user.getUserId()));

                        return user;
                    } else {
                        return null;
                    }
                }
            }
        }
    }

    private User mapFromResultSet(ResultSet resultSet) throws SQLException {
        var user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setDateOfBirth(resultSet.getString("date_of_birth"));
        user.setEmail(resultSet.getString("email"));
        user.setProfilePicture(resultSet.getString("profile_picture"));
        return user;
    }

    private List<Category> retrieveUserPreferences(int userId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = """
                    SELECT *
                    FROM Preferences.preferences
                    JOIN Users
                        ON Users.user_id = Preferences.user_id
                    WHERE user_id = ?
                    """;

            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);

                try (var resultSet = statement.executeQuery()) {
                    List<Category> preferences = new ArrayList<>();

                    while (resultSet.next()) {
                        preferences.add(Category.valueOf(resultSet.getString("preference")));
                    }
                    return preferences;
                }
            }
        }
    }

}

















