package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import no.kristiania.collectthemunch.SampleData;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewEventDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final ReviewEventDao reviewEventDao = new ReviewEventDao(dataSource);
    private final UserDao userDao = new UserDao(dataSource);
    private final EventDao eventDao = new EventDao(dataSource);

    @Test
    void shouldAddAndGetEventReview() throws SQLException, ItemNotSavedException {

        for (int i = 0; i < 1000; i++) {
            var user = SampleData.sampleUser();
            var event = SampleData.sampleEvent();
            var eventReview = SampleData.sampleReview();
            eventReview.setUserName(user.getUsername());
            eventReview.setProfilePicture(user.getProfilePicture());

            userDao.saveUser(user);
            eventDao.save(event);
            reviewEventDao.save(eventReview, event.getId(), user.getUserId());

            assertThat(reviewEventDao.getReviewFromUserOnEvent(event.getId(), user.getUserId()))
                    .hasNoNullFieldsOrProperties()
                    .usingRecursiveComparison()
                    .isEqualTo(eventReview)
                    .isNotSameAs(eventReview);
        }
    }
}
