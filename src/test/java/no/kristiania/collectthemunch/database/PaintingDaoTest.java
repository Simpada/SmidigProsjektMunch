package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import no.kristiania.collectthemunch.entities.Painting;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static no.kristiania.collectthemunch.SampleData.samplePainting;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaintingDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final PaintingDao paintingDao = new PaintingDao(dataSource);


    @Test
    void shouldSaveAndRetrieveUser() throws SQLException {
        var painting = samplePainting();
        painting.setPaintingImage(new byte[]{1,2});
        paintingDao.save(painting);

        var painting2 = paintingDao.retrieve(painting.getPaintingId());

        assertThat(painting2)
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(painting)
                .isNotSameAs(painting);
    }

    @Test
    void shouldRetrieveAll() throws SQLException {
        int numOfPaintings = 10;
        int flywayData = 5;

        for (int i = 0; i < numOfPaintings; i++) {
           paintingDao.save(samplePainting());
        }

        List<Painting> retrievedFromDb = paintingDao.retrieveAll();

        assertEquals(numOfPaintings + flywayData, retrievedFromDb.size());
    }

    @Test
    void shouldRetrieveAllFromUser() {
        /*
            Create user
            add paintings to user
            print all
         */


    }


}
