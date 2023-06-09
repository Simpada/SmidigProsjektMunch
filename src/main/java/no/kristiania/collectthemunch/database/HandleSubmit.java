package no.kristiania.collectthemunch.database;

import java.sql.SQLException;

public interface HandleSubmit {
    void execute() throws ItemNotSavedException, SQLException;
}
