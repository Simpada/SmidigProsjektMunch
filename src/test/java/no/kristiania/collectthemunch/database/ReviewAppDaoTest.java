package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import no.kristiania.collectthemunch.SampleData;
import no.kristiania.collectthemunch.entities.Review;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.List;

public class ReviewAppDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final ReviewAppDao reviewAppDao = new ReviewAppDao(dataSource);
    private final UserDao userDao = new UserDao(dataSource);

    public ReviewAppDaoTest() {
    }

    @Test
    void shouldSaveAppReviewInDatabase() throws SQLException {
        var review = SampleData.sampleReview();
        var user = SampleData.sampleUser();

        userDao.save(user);
        reviewAppDao.save(review, user.getUserId());

        assertThat(reviewAppDao.retrieveAppReviewById(user.getUserId()))
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isNotSameAs(review)
                .isEqualTo(review);
    }

    @Test
    void shouldRetrieveAllAppReviewsInDatabase() throws SQLException {
        for (int i = 0; i < 5; i++) {
            var review = SampleData.sampleReview();
            var user = SampleData.sampleUser();

            userDao.save(user);
            reviewAppDao.save(review, user.getUserId());
            System.out.println(review.getReviewText());
        }
        System.out.println(" --- ");
        List<Review> appReviews = reviewAppDao.retrieveAllAppReviews();

        assertNotNull(appReviews);
        assertEquals(5, appReviews.size());

        for (Review review : appReviews) {
            assertThat(review).hasNoNullFieldsOrProperties();
        }
    }
}
