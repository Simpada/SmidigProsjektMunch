package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import no.kristiania.collectthemunch.entities.Review;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewEventDao extends AbstractDao {

    @Inject
    public ReviewEventDao(DataSource dataSource) {
        super(dataSource);
    }

    public void save(Review review, int eventId, int userId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            var query = "INSERT INTO Event_Reviews(user_id, event_id, review_text, num_stars) VALUES (?,?,?,?)";
            try (var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, userId);
                statement.setInt(2, eventId);
                statement.setString(3, review.getReviewText());
                statement.setInt(4, review.getNumOfStars());

                statement.executeUpdate();

                try (var generatedKeys = statement.getGeneratedKeys()) {
                    generatedKeys.next();
                    review.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Review getReviewFromUserOnEvent(int eventId, int userId) throws SQLException {
        Review review = new Review();

        try (var connection = dataSource.getConnection()) {
            var query = "SELECT * FROM Event_Reviews JOIN Users on Users.user_id = Event_Reviews.user_id JOIN Events on Events.event_id = Event_Reviews.event_id WHERE Users.user_id = ? AND Events.event_id = ?";
            try (var statement = connection.prepareStatement(query))  {
                statement.setInt(1, userId);
                statement.setInt(2, eventId);
                try (var response = statement.executeQuery()) {
                    while (response.next()) {
                        review = mapFromResultSet(response);
                    }

                }
            }
        }
        return review;
    }

    public List<Review> getAllReviewsFromEvent(int eventId) throws SQLException {
        List<Review> reviews = new ArrayList<>();

        try (var connection = dataSource.getConnection()) {
            var query = "SELECT * FROM Event_Reviews JOIN Users U on U.user_id = Event_Reviews.user_id WHERE event_id = ?";
            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, eventId);
                try (var response = statement.executeQuery()) {
                    while (response.next()) {
                        reviews.add(mapFromResultSet(response));
                    }
                }

            }
        }
        return reviews;
    }

    public List<Review> getAllEventReviewsFromUser(int userId) throws SQLException {
        List<Review> reviews = new ArrayList<>();

        try (var connection = dataSource.getConnection()) {
            var query = "SELECT * FROM Event_Reviews JOIN Users U on U.user_id = Event_Reviews.user_id WHERE U.user_id = ?";
            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                try (var response = statement.executeQuery()) {
                    while (response.next()) {
                        reviews.add(mapFromResultSet(response));
                    }
                }

            }
        }
        return reviews;
    }

    private Review mapFromResultSet(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getInt("review_id"));
        review.setUserName(resultSet.getString("username"));
        review.setProfilePicture(resultSet.getBytes("profile_picture"));
        review.setReviewText(resultSet.getString("review_text"));
        review.setNumOfStars(resultSet.getInt("num_stars"));
        return review;
    }
}
