package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import no.kristiania.collectthemunch.entities.User;

import javax.sql.DataSource;

public class UserDao extends AbstractDao {

    @Inject
    public UserDao(DataSource dataSource) {
        super(dataSource);
    }

    public Boolean save(User user) {

        return false;
    }

    public User retrieve(int userId) {

        return null;
    }

}
