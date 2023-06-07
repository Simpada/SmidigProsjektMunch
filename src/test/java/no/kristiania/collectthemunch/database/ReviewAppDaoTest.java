package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import no.kristiania.collectthemunch.SampleData;
import no.kristiania.collectthemunch.entities.Review;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReviewAppDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final ReviewAppDao reviewAppDao = new ReviewAppDao(dataSource);
    private final UserDao userDao = new UserDao(dataSource);

    public ReviewAppDaoTest() {
    }

    @Test
    void shouldSaveAppReviewInDatabase() throws SQLException {

        for (int i = 0; i < 1000; i++) {

            var review = SampleData.sampleReview();
            var user = SampleData.sampleUser();
            review.setUserName(user.getUsername());
            review.setProfilePicture(user.getProfilePicture());

            userDao.save(user);
            reviewAppDao.save(review, user.getUserId());

            System.out.println(Arrays.toString(review.getProfilePicture()));

            System.out.println(Arrays.toString(reviewAppDao.retrieveAppReviewById(user.getUserId()).getProfilePicture()));

            assertThat(reviewAppDao.retrieveAppReviewById(user.getUserId()))
                    .hasNoNullFieldsOrProperties()
                    .usingRecursiveComparison()
                    .isNotSameAs(review)
                    .isEqualTo(review);

        }
    }

    @Test
    void shouldRetrieveAllAppReviewsInDatabase() throws SQLException {
        List<Review> appReviews = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            var review = SampleData.sampleReview();
            var user = SampleData.sampleUser();
            review.setUserName(user.getUsername());
            review.setProfilePicture(user.getProfilePicture());

            userDao.save(user);
            reviewAppDao.save(review, user.getUserId());
            appReviews.add(review);
        }

        List<Review> reviewsFromDb = reviewAppDao.retrieveAllAppReviews();

        for (Review review : appReviews) {
            assertThat(reviewsFromDb)
                    .extracting(Review::getUserName)
                    .contains(review.getUserName());
        }
    }

    @Test
    void shouldRetrieveAllAppReviewsWithSpecificStarsInDatabase() throws SQLException {
        var review = SampleData.sampleReview();
        var user = SampleData.sampleUser();
        review.setNumOfStars(4);
        review.setUserName(user.getUsername());
        review.setProfilePicture(user.getProfilePicture());

        userDao.save(user);
        reviewAppDao.save(review, user.getUserId());

        assertThat(reviewAppDao.retrieveAppReviewsByStars(4))
                .extracting(Review::getUserName)
                .contains(review.getUserName());
    }
}
