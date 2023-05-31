package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import no.kristiania.collectthemunch.entities.Category;
import no.kristiania.collectthemunch.entities.Event;
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

    public Boolean save(User user) {
        return false;
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
                        user.setEvents(retrieveUserEvents(user.getUserId()));

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
                        preferences.add(Category.valueOf(resultSet.getString("preferences")));
                    }
                    return preferences;
                }
            }
        }
    }

    private List<Event> retrieveUserEvents(int userId) {
        return null;
    }


}

















