package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import no.kristiania.collectthemunch.SampleData;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.sql.SQLException;

public class ReviewAppDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final ReviewAppDao reviewAppDao = new ReviewAppDao(dataSource);

    public ReviewAppDaoTest() {
    }

    @Test
    void shouldSaveAppReviewInDatabase() throws SQLException {
        var review = SampleData.sampleReview();
        reviewAppDao.save(review);

        //assertThat();
    }
}
