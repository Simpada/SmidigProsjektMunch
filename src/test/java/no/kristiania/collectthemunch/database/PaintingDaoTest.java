package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import no.kristiania.collectthemunch.entities.Painting;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static no.kristiania.collectthemunch.SampleData.samplePainting;
import static no.kristiania.collectthemunch.SampleData.sampleUser;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaintingDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final PaintingDao paintingDao = new PaintingDao(dataSource);
    private final UserDao userdao = new UserDao(dataSource);


    @Test
    void shouldSaveAndRetrieveUser() throws SQLException, ItemNotSavedException {
        var painting = samplePainting();
        painting.setPaintingImage(new byte[]{1,2});
        paintingDao.save(painting);

        var painting2 = paintingDao.retrievePaintingById(painting.getPaintingId());

        assertThat(painting2)
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(painting)
                .isNotSameAs(painting);
    }

    @Test
    void shouldRetrieveAll() throws SQLException, ItemNotSavedException {
        int numOfPaintings = 10;
        int flywayData = 5;

        for (int i = 0; i < numOfPaintings; i++) {
           paintingDao.save(samplePainting());
        }

        List<Painting> paintings = paintingDao.retrieveAllPaintings();

        assertEquals(numOfPaintings + flywayData, paintings.size());
    }

    @Test
    void shouldRetrieveAllFromUser() throws SQLException, ItemNotSavedException {
        var p1 = paintingDao.retrievePaintingById(1);
        var p2 = paintingDao.retrievePaintingById(2);
        var p3 = paintingDao.retrievePaintingById(3);

        var user = sampleUser();
        userdao.saveUser(user);

        paintingDao.saveToInventory(user.getUserId(), p1.getPaintingId());
        paintingDao.saveToInventory(user.getUserId(), p2.getPaintingId());
        paintingDao.saveToInventory(user.getUserId(), p3.getPaintingId());


        List<Painting> paintingsInventory = paintingDao.retrieveAllPaintingsByUserId(user.getUserId());

        assertEquals(3, paintingsInventory.size());


        assertThat(paintingsInventory.get(0))
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(p1)
                .isNotSameAs(p1);

        assertThat(paintingsInventory.get(1))
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(p2)
                .isNotSameAs(p2);

        assertThat(paintingsInventory.get(2))
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(p3)
                .isNotSameAs(p3);
    }

}
