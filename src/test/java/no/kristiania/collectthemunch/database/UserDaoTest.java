package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static no.kristiania.collectthemunch.SampleData.sampleUser;
import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final UserDao userDao = new UserDao(dataSource);

    public UserDaoTest() throws IOException {
    }

    @Test
    void shouldSaveAndRetrieveUser() throws SQLException {
        var user = sampleUser();
        userDao.save(user);

        System.out.println(user);
        user.printPreferences();

        assertThat(userDao.retrieve(user.getUserId()))
                .hasNoNullFieldsOrProperties()
                .isEqualTo(user)
                .isNotSameAs(user);
    }
}
