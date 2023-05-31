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
        saveReview(review, userId);
        connectToEvent(review, eventId);
    }

    private void saveReview(Review review, int userId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            var query = "INSERT INTO Reviews(user_id, review_text, num_stars) VALUES (?,?,?)";
            try (var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, userId);
                statement.setString(2, review.getReviewText());
                statement.setInt(3, review.getNumOfStars());

                statement.executeUpdate();

                try (var generatedKeys = statement.getGeneratedKeys()) {
                    generatedKeys.next();
                    review.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    private void connectToEvent(Review review, int eventId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            var query = "INSERT INTO Event_Reviews(event_id, review_id) VALUES (?,?)";
            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, eventId);
                statement.setInt(2, review.getId());

                statement.executeUpdate();

            }
        }
    }

    public Review getReviewFromUserOnEvent(int eventId, int userId) throws SQLException {
        Review review = new Review();

        try (var connection = dataSource.getConnection()) {
            var query = "SELECT ER.review_id, ER.review_text, ER.num_stars FROM Event_Reviews ER JOIN Users on Users.user_id = ER.user_id JOIN Events on Events.event_id = ER.event_id";
            try (var statement = connection.prepareStatement(query))  {
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
