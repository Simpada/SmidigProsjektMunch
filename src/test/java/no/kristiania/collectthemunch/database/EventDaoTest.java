package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import no.kristiania.collectthemunch.SampleData;
import no.kristiania.collectthemunch.entities.Event;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final EventDao eventDao = new EventDao(dataSource);

    public EventDaoTest() {
    }

    @Test
    void shouldSaveEventInDatabase() throws SQLException {
        // Verifies that the getAllEventsFromDatabase() method retrieves events from
        // the database and correctly maps them to Event objects with their corresponding categories.

        for (int i = 0; i < 20; i++) {
            var event = SampleData.sampleEvent();

            eventDao.save(event);
            eventDao.saveEventCategories(event);

            var returnedEvent = eventDao.getEventById(event.getId());
            System.out.println(returnedEvent.getName());

            assertThat(returnedEvent)
                    .hasNoNullFieldsOrProperties()
                    .usingRecursiveComparison()
                    .ignoringCollectionOrder()
                    .isEqualTo(event)
                    .isNotSameAs(event);
        }
    }

    @Test
    void shouldRetrieveAllEvents() throws SQLException {
        List<Event> events = eventDao.getAllEvents();

        for (Event e : events) {
            assertNotNull(e);
        }
    }

    @Test
    void shouldRetrieveSpecificEvents() {
        List<String> myPreferences = new ArrayList<>();
        myPreferences.add("GAMES");
        myPreferences.add("EXHIBITION");
        myPreferences.add("PARTY");

        List<Event> filteredEvents = eventDao.getFilteredEvents(myPreferences);

        for (Event e : filteredEvents) {
            System.out.println(e.getId());
        }
    }


}
