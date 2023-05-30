package no.kristiania.collectthemunch;

import no.kristiania.collectthemunch.database.Database;
import no.kristiania.collectthemunch.server.CollectTheMunchServer;

import javax.sql.DataSource;
import java.util.Optional;

public class Program {

    public static void main(String[] args) throws Exception {

        DataSource dataSource = Database.getDataSource();

        var port = Optional.ofNullable(System.getenv("HTTP_PLATFORM_PORT"))
                .map(Integer::parseInt)
                .orElse(8080);

        new CollectTheMunchServer(port, DatabaseDataSource.getDatasource()).start();
    }
}
