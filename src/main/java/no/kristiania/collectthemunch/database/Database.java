package no.kristiania.collectthemunch.database;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Database {

    public static DataSource getDataSource(Map<String, String> environment) throws IOException {

        HikariDataSource source = new HikariDataSource();

        String url = environment.get("JDBC_URL");
        String username = environment.get("JDBC_USERNAME");
        String password = environment.get("JDBC_PASSWORD");
        if (
                url == null ||
                        username == null ||
                        password == null
        ) {
            var properties = new Properties();
            try (var file = new FileReader("application.properties")) {
                properties.load(file);
            }
            source.setJdbcUrl(properties.getProperty("jdbc.url"));
            source.setUsername(properties.getProperty("jdbc.username"));
            source.setPassword(properties.getProperty("jdbc.password"));
        } else {
            source.setJdbcUrl(url);
            source.setUsername(username);
            source.setPassword(password);
        }

        Flyway.configure()
                .dataSource(source)
                .load()
                .migrate();

        return source;
    }

}
