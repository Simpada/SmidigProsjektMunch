package no.kristiania.collectthemunch.database;


import no.kristiania.collectthemunch.MemoryDataSource;
import no.kristiania.collectthemunch.SampleData;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.sql.SQLException;

public class EventDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final EventDao eventDao = new EventDao(dataSource);

    public EventDaoTest() {
    }

    @Test
    void shouldSaveEventInDatabase() throws SQLException {
        // Verifies that the getAllEventsFromDatabase() method retrieves events from
        // the database and correctly maps them to Event objects with their corresponding categories.

        for (int i = 0; i < 5; i++) {
            var event = SampleData.sampleEvent();

            eventDao.save(event);
            eventDao.saveCategoriesByEvent(event);
            System.out.println(event.getId());
            System.out.println(event.getDescription());
            System.out.println(event.getName());
            System.out.println(event.getCategories());

            var returnedEvent = eventDao.getEventById(event.getId());

            System.out.println(returnedEvent.getId());
            System.out.println(returnedEvent.getDescription());
            System.out.println(returnedEvent.getName());
            System.out.println(returnedEvent.getCategories());

            assertThat(returnedEvent)
                    .hasNoNullFieldsOrProperties()
                    .usingRecursiveComparison()
                    .isEqualTo(event)
                    .isNotSameAs(event);
        }

    }

}
