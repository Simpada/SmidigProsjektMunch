package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import no.kristiania.collectthemunch.entities.Review;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewEventDao extends AbstractDao {

    @Inject
    public ReviewEventDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<Review> retrieveAllEventReviews() throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT ER.review_id, ER.review_text, ER.num_stars, U.username, U.profile_picture FROM Event_Reviews ER JOIN Users U on U.user_id = ER.user_id";
            try(var statement = connection.prepareStatement(query)) {
                try (var resultSet = statement.executeQuery()) {
                    List<Review> resultReviews = new ArrayList<>();
                    while (resultSet.next()) {
                        resultReviews.add(ReviewResultMapping.mapReviews(resultSet));
                    }
                    if (resultReviews.isEmpty()) {
                        throw new NotFoundException("No reviews found in database");
                    }
                    return resultReviews;
                }
            }
        }
    }

    public void save(Review review, int eventId, int userId) throws SQLException, ItemNotSavedException {
        try (var connection = dataSource.getConnection()) {
            var query = "INSERT INTO Event_Reviews(user_id, event_id, review_text, num_stars) VALUES (?,?,?,?)";
            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.setInt(2, eventId);
                statement.setString(3, review.getReviewText());
                statement.setInt(4, review.getNumOfStars());
                statement.executeUpdate();
                try (var generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        review.setUserId(generatedKeys.getInt(1));
                    } else {
                        throw new ItemNotSavedException("Could not save review from user id " + userId +  " for event id " + eventId);
                    }
                }
            }
        }
    }

    public Review retrieveReviewFromUserOnEvent(int eventId, int userId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            var query = "SELECT * FROM Event_Reviews JOIN Users on Users.user_id = Event_Reviews.user_id JOIN Events on Events.event_id = Event_Reviews.event_id WHERE Users.user_id = ? AND Events.event_id = ?";
            try (var statement = connection.prepareStatement(query))  {
                statement.setInt(1, userId);
                statement.setInt(2, eventId);
                try (var response = statement.executeQuery()) {
                    var review = new Review();
                    if (response.next()) {
                        review = ReviewResultMapping.mapReviews(response);
                    } else {
                        throw new NotFoundException("No review found from user with id " + userId + " on event id " + eventId);
                    }
                    return review;
                }
            }
        }
    }

    public List<Review> retrieveAllReviewsFromEvent(int eventId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            var query = "SELECT * FROM Event_Reviews JOIN Users U on U.user_id = Event_Reviews.user_id WHERE event_id = ?";
            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, eventId);
                try (var response = statement.executeQuery()) {
                    List<Review> result = new ArrayList<>();
                    while (response.next()) {
                        result.add(mapFromResultSet(response));
                    }
                    if (result.isEmpty()) {
                        throw new NotFoundException("No reviews found for event id " + eventId);
                    }
                    return result;
                }
            }
        }
    }

    public List<Review> retrieveAllEventReviewsFromUser(int userId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            var query = "SELECT * FROM Event_Reviews JOIN Users U on U.user_id = Event_Reviews.user_id WHERE U.user_id = ?";
            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                try (var resultSet = statement.executeQuery()) {
                    List<Review> result = new ArrayList<>();
                    while (resultSet.next()) {
                        reviews.add(ReviewResultMapping.mapReviews(response));
                    }
                    if (result.isEmpty()) {
                        throw new NotFoundException("No reviews found for user id " + userId);
                    }
                    return result;
                }
            }
        }
    }
}

