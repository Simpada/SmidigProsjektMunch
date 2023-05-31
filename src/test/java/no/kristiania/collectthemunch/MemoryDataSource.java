package no.kristiania.collectthemunch;

import no.kristiania.collectthemunch.database.Database;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;

public class MemoryDataSource {

    public static DataSource createTestDataSource() throws IOException {

        HashMap<String, String> environment = new HashMap<>();
        environment.put("JDBC_URL", "jdbc:h2:mem:collectTheMunchTestDB;DB_CLOSE_DELAY=-1;MODE=MSSQLServer");
        environment.put("JDBC_USERNAME", "sa");
        environment.put("JDBC_PASSWORD", "");
        return Database.getDataSource(environment);
    }
}
