package no.kristiania.collectthemunch;

import no.kristiania.collectthemunch.database.Database;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;

public class MemoryDataSource {

    public static DataSource createTestDataSource() {

        var dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:testDatabase;DB_CLOSE_DELAY=-1;MODE=MSSQLServer");
        var flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
        return dataSource;

    }
}
