package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewEventDaoTest {

    private ReviewEventDao dao;

    @BeforeEach
    void setUp() throws IOException {
        dao = new ReviewEventDao(MemoryDataSource.createTestDataSource());
    }

    @Test
    void shouldAddAndGetEventReview() {



    }

}
