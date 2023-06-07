package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import no.kristiania.collectthemunch.entities.Review;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewAppDao extends AbstractDao{

    @Inject
    public ReviewAppDao(DataSource dataSource) {
        super(dataSource);
    }

    public void save(Review review, int userId) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "INSERT INTO App_Reviews (user_id, review_text, num_stars) VALUES(?, ?, ?)";
            try(var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.setString(2, review.getReviewText());
                statement.setInt(3, review.getNumOfStars());
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected == 0) {
                    throw new SQLException("Failed to save the review for user id: " + userId);
                }
            }
        }
    }

    public List<Review> retrieveAllAppReviews() throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM App_Reviews JOIN Users U on U.user_id = App_Reviews.user_id";
            try(var statement = connection.prepareStatement(query)){
                try(var resultSet = statement.executeQuery()) {
                    List<Review> result = new ArrayList<>();
                    while (resultSet.next()) {
                        result.add(mapFromResultSet(resultSet));
                    }
                    if (result.isEmpty()) {
                        throw new NotFoundException("No reviews registered for this app");
                    }
                    return result;
                }
            }
        }
    }

    public Review retrieveAppReviewById(int userId) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM App_Reviews JOIN Users U on U.user_id = App_Reviews.user_id WHERE U.user_id = ?";
            try(var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                try(var resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapFromResultSet(resultSet);
                    } else {
                        // Review not found, throw a not found exception with user id
                        throw new NotFoundException("Review not found with ID: " + userId);
                    }
                }
            }
        }
    }

    public List<Review> retrieveAppReviewsByStars(int numStars) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * From App_Reviews JOIN Users U on U.user_id = App_Reviews.user_id WHERE num_stars = ?";
            try(var statement = connection.prepareStatement(query)) {
                statement.setInt(1, numStars);
                try(var resultSet = statement.executeQuery()) {
                    List<Review> result = new ArrayList<>();
                    while (resultSet.next()) {
                        result.add(mapFromResultSet(resultSet));
                    }
                    if (result.isEmpty()) {
                        throw new NotFoundException("No reviews with " + numStars + " stars found.");
                    }
                    return result;
                }
            }
        }
    }

    private Review mapFromResultSet(ResultSet resultSet) throws SQLException {
        var review = new Review();
        review.setUserId(resultSet.getInt("user_id"));
        review.setUserName(resultSet.getString("username"));
        review.setProfilePicture(resultSet.getBytes("profile_picture"));
        review.setReviewText(resultSet.getString("review_text"));
        review.setNumOfStars(resultSet.getInt("num_stars"));
        return review;
    }
}
