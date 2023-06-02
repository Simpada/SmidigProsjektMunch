package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import no.kristiania.collectthemunch.entities.Review;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

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
                        review.setId(response.getInt("review_id"));
                        review.setReviewText(response.getString("review_text"));
                        review.setNumOfStars(response.getInt("num_stars"));
                    }
                    return review;
                }
            }
        }
    }
}
