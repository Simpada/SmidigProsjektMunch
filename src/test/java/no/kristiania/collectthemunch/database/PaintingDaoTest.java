package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

public class PaintingDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final PaintingDao paintingDao = new PaintingDao(dataSource);


    @Test
    void shouldSaveAndRetrieveUser() {
        
    }





}
