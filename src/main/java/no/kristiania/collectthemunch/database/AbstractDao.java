package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;

import javax.sql.DataSource;

public abstract class AbstractDao {

    protected final DataSource dataSource;

    @Inject
    public AbstractDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}