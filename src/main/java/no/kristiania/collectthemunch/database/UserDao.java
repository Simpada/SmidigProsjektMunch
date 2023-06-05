package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import no.kristiania.collectthemunch.entities.Category;
import no.kristiania.collectthemunch.entities.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao {

    @Inject
    public UserDao(DataSource dataSource) {
        super(dataSource);
    }

    public void save(User user) throws SQLException {
        saveUser(user);
        saveUserPreferences(user);
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

    public void saveUserPreferences(User user) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "INSERT INTO Preferences (user_id, preference) VALUES (?, ?)";

            for (Category c : user.getPreferences()) {
                try (var statement = connection.prepareStatement(query)) {
                    statement.setInt(1, user.getUserId());
                    statement.setString(2, String.valueOf(c));
                    statement.executeUpdate();
                }
            }
        }
    }

    public List<User> retrieveAll() throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Users";

            try (var statement = connection.prepareStatement(query)) {
                try (var resultSet = statement.executeQuery()) {
                    List<User> users = new ArrayList<>();

                    while (resultSet.next()) {
                        var user = new User();
                        user = mapFromResultSet(resultSet);
                        user.setPreferences(retrieveUserPreferences(user.getUserId()));
                        users.add(user);
                    }
                    return users;
                }
            }
        }
    }

    //Retrieve by id
    public User retrieve(int userId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Users WHERE user_id = ?";

            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);

                return getUser(statement);
            }
        }
    }

    //Retrieve by username
    public User retrieve(String username) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Users WHERE username = ?";

            try (var statement = connection.prepareStatement(query)) {
                statement.setString(1, username);

                return getUser(statement);
            }
        }
    }

    private User getUser(PreparedStatement statement) throws SQLException {
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

    public void updatePreferences(int userId, List<Category> preferences) throws SQLException {
        removeUserPreferences(userId);

        try (var connection = dataSource.getConnection()) {
            String query = "INSERT INTO Preferences (user_id, preference) VALUES (?,?)";

            for (Category c : preferences) {
                try (var statement = connection.prepareStatement(query)) {
                    statement.setInt(1, userId);
                    statement.setString(2, String.valueOf(c));
                    statement.executeUpdate();
                }
            }
        }
    }

    public void removeUserPreferences(int userId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "DELETE FROM Preferences WHERE user_id = ?";

            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.executeUpdate();
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
                    FROM preferences
                    JOIN Users
                        ON Users.user_id = Preferences.user_id
                    WHERE preferences.user_id = ?
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


