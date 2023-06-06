package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import no.kristiania.collectthemunch.entities.Review;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

public class ReviewAppDao extends AbstractDao{

    @Inject
    public ReviewAppDao(DataSource dataSource) {
        super(dataSource);
    }

    public void save(Review review) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "INSERT INTO App_Reviews (review_text, num_stars) VALUES(?, ?)";
            try(var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, review.getReviewText());
                statement.setInt(2, review.getNumOfStars());
                statement.executeUpdate();
                try(var generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        review.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    public Review retrieveReviewById(int userId) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM App_Reviews WHERE user_id = ?";
            try(var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                try(var resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        var review = new Review();
                        review.setId(resultSet.getInt("user_id"));
                        review.setReviewText(resultSet.getString("review_text"));
                        review.setNumOfStars(resultSet.getInt("num_stars"));
                        return review;
                    } else {
                        // Review not found, throw a not found exception with user id
                        throw new NotFoundException("Review not found with ID: " + userId);
                    }
                }
            }
        }
    }

}
