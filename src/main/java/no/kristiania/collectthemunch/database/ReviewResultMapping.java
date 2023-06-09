package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.entities.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewResultMapping {

    static Review mapReviews(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setUserId(resultSet.getInt("user_id"));
        review.setUserName(resultSet.getString("username"));
        review.setProfilePicture(resultSet.getBytes("profile_picture"));
        review.setReviewText(resultSet.getString("review_text"));
        review.setNumOfStars(resultSet.getInt("num_stars"));
        return review;
    }
}
