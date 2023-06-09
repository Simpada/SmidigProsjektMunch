package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public abstract class AbstractDao {

    protected final DataSource dataSource;

    @Inject
    public AbstractDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}