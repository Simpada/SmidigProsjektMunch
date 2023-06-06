package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static no.kristiania.collectthemunch.SampleData.samplePainting;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PaintingDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final PaintingDao paintingDao = new PaintingDao(dataSource);

/*
    @Test
    void shouldSaveAndRetrieveUser() throws SQLException {
        var painting = samplePainting();
        paintingDao.save(painting);

        var painting2 = paintingDao.retrieve(painting.getPaintingId());

        assertThat(painting2)
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(painting)
                .isNotSameAs(painting);
    }

*/
}
