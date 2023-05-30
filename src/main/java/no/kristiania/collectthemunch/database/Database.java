package no.kristiania.collectthemunch.database;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Database {

    public static DataSource getDataSource() throws IOException {

        HikariDataSource source = new HikariDataSource();

        var properties = new Properties();
        try (var file = new FileReader("application.properties")) {
            properties.load(file);
        }
        source.setJdbcUrl(properties.getProperty("jdbc.url"));
        source.setUsername(properties.getProperty("jdbc.username"));
        source.setPassword(properties.getProperty("jdbc.password"));

        Flyway.configure()
                .dataSource(source)
                .load()
                .migrate();

        return source;
    }

}
