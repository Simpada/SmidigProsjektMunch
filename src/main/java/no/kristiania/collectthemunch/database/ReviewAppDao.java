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
            try(var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, userId);
                statement.setString(2, review.getReviewText());
                statement.setInt(3, review.getNumOfStars());
                statement.executeUpdate();
                try(var generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        review.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    public List<Review> retrieveAllAppReviews() throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM App_Reviews";
            try(var statement = connection.prepareStatement(query)){
                try(var resultSet = statement.executeQuery()) {
                    List<Review> resultReviews = new ArrayList<>();
                    if (resultSet.next()) {
                        do {
                            resultReviews.add(mapFromResultSet(resultSet));
                        } while (resultSet.next());
                        return resultReviews;
                    } else {
                        throw new NotFoundException("No reviews registered for this app");
                    }
                }
            }
        }
    }

    public Review retrieveAppReviewById(int userId) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM App_Reviews WHERE user_id = ?";
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
            String query = "SELECT * From App_Reviews WHERE num_stars = ?";
            try(var statement = connection.prepareStatement(query)) {
                statement.setInt(1, numStars);
                try(var resultSet = statement.executeQuery()) {
                    List<Review> resultReviews = new ArrayList<>();
                    if (resultSet.next()) {
                        do {
                            resultReviews.add(mapFromResultSet(resultSet));
                        } while (resultSet.next());
                        return resultReviews;
                    } else {
                        throw new NotFoundException("No reviews with " + numStars + " stars found.");
                    }
                }
            }
        }
    }

    private Review mapFromResultSet(ResultSet resultSet) throws SQLException {
        var review = new Review();
        review.setId(resultSet.getInt("user_id"));
        review.setReviewText(resultSet.getString("review_text"));
        review.setNumOfStars(resultSet.getInt("num_stars"));
        return review;
    }

}
