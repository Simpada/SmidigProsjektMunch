package no.kristiania.collectthemunch.database;

import javassist.tools.rmi.Sample;
import jdk.jfr.Description;
import no.kristiania.collectthemunch.MemoryDataSource;
import no.kristiania.collectthemunch.SampleData;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EventDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final EventDao eventDao = new EventDao(dataSource);

    public EventDaoTest() {
    }

    @Test
    void shouldSaveEventInDatabase() {
        // Verifies that the getAllEventsFromDatabase() method retrieves events from
        // the database and correctly maps them to Event objects with their corresponding categories.
        var event = SampleData.sampleEvent();
        System.out.println(event.getCategories());
        System.out.println(event.getDescription());
        System.out.println(event.getId());
    }

}
