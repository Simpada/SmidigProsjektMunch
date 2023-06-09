package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
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

    private void save(User user) throws SQLException {
        if (user.getProfilePicture() == null) {
            user.setProfilePicture(new byte[1]);
        }

        try (var connection = dataSource.getConnection()) {
            String query = "INSERT INTO Users (username, password, date_of_birth, email, profile_picture) VALUES (?, ?, ?, ?, ?)";

            try (var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getDateOfBirth());
                statement.setString(4, user.getEmail());
                statement.setBytes(5, user.getProfilePicture());

                statement.executeUpdate();
                try (var generatedKeys = statement.getGeneratedKeys()) {
                    generatedKeys.next();
                    user.setUserId(generatedKeys.getInt(1));
                }
            }
        }
    }

    private void updateUserData(User updatedUser) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "UPDATE Users SET username=?, password=?, date_of_birth=?, email=?, profile_picture=? WHERE user_id=?";

            try (var statement = connection.prepareStatement(query)) {
                statement.setString(1, updatedUser.getUsername());
                statement.setString(2, updatedUser.getPassword());
                statement.setString(3, updatedUser.getDateOfBirth());
                statement.setString(4, updatedUser.getEmail());
                statement.setBytes(5, updatedUser.getProfilePicture());
                statement.setInt(6, updatedUser.getUserId());

                statement.executeUpdate();
            }
        }
    }

    public void saveUserPreferences(User user) throws SQLException {
        if (user.getPreferences() == null) {
            return;
        }

        try (var connection = dataSource.getConnection()) {
            String query = "INSERT INTO Preferences (user_id, preference) VALUES (?, ?)";

            for (String c : user.getPreferences()) {
                try (var statement = connection.prepareStatement(query)) {
                    statement.setInt(1, user.getUserId());
                    statement.setString(2, String.valueOf(c));
                    statement.executeUpdate();
                }
            }
        }
    }

    private void validateUniqueUsername(String username) throws SQLException, ItemNotSavedException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT COUNT(*) FROM Users WHERE username = ?";
            try (var statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                try (var resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        if (resultSet.getInt(1) > 0) {
                            throw new ItemNotSavedException("Username " + username + " already exists in database");
                        }
                    }
                }
            }
        }
    }

    private void validateUniqueEmail(String email) throws SQLException, ItemNotSavedException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT COUNT(*) FROM Users WHERE email = ?";
            try (var statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                try (var resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        if (resultSet.getInt(1) > 0) {
                            throw new ItemNotSavedException("User with email " + email + " already exists in database");
                        }
                    }
                }
            }
        }
    }

    public User login(String username, String password) throws SQLException {
        User user = retrieveUserByName(username);

        if (user == null || !password.equals(user.getPassword())) {
            System.out.println("No user or wrong login/password");
            return null;
        } else {
            return user;
        }
    }

    public List<User> retrieveAllUsers() throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Users";
            try (var statement = connection.prepareStatement(query)) {
                try (var resultSet = statement.executeQuery()) {
                    List<User> users = new ArrayList<>();

                    while (resultSet.next()) {
                        var user = mapFromResultSet(resultSet);
                        user.setPreferences(retrieveUserPreferences(user.getUserId()));
                        users.add(user);
                    }
                    if (users.isEmpty()) {
                        throw new NotFoundException("No users found in database");
                    }
                    return users;
                }
            }
        }
    }

    public User retrieveUserById(int userId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Users WHERE user_id = ?";
            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);

                return getUser(statement);
            }
        }
    }

    public User retrieveUserByName(String username) throws SQLException {
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
                var user = mapFromResultSet(resultSet);
                user.setPreferences(retrieveUserPreferences(user.getUserId()));

                return user;
            } else {
                throw new NotFoundException("User not found");
            }
        }
    }

    public void updatePreferences(int userId, List<String> preferences) throws SQLException {
        removeUserPreferences(userId);

        try (var connection = dataSource.getConnection()) {
            String query = "INSERT INTO Preferences (user_id, preference) VALUES (?,?)";

            for (String c : preferences) {
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

    public List<String> retrieveUserPreferences(int userId) throws SQLException {
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
                    List<String> preferences = new ArrayList<>();

                    while (resultSet.next()) {
                        preferences.add(resultSet.getString("preference"));
                    }
                    return preferences;
                }
            }
        }
    }

    private void retrievePoints(User user) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Points WHERE user_id = ?";
            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, user.getUserId());
                try (var resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        user.setCurrentPoints(resultSet.getInt("current_points"));
                        user.setWeeklyPoints(resultSet.getInt("weekly_points"));
                        user.setMonthlyPoints(resultSet.getInt("monthly_points"));
                        user.setAllTimePoints(resultSet.getInt("alltime_points"));
                    }
                }
            }
        }
    }

    public User updateUser(User updatedUser) throws SQLException {
        updateUserData(updatedUser);
        return retrieveUserById(updatedUser.getUserId());
    }

    public void saveUser(User user) throws SQLException, ItemNotSavedException {
        validateUniqueUsername(user.getUsername());
        validateUniqueEmail(user.getEmail());
        save(user);
        saveUserPreferences(user);
    }

    private User mapFromResultSet(ResultSet resultSet) throws SQLException {
        var user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setDateOfBirth(resultSet.getString("date_of_birth"));
        user.setEmail(resultSet.getString("email"));
        user.setProfilePicture(resultSet.getBytes("profile_picture"));
        retrievePoints(user);
        return user;
    }
}
