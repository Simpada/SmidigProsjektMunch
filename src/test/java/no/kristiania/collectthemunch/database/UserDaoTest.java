package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static no.kristiania.collectthemunch.SampleData.sampleUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
                .usingRecursiveComparison()
                .isEqualTo(user)
                .isNotSameAs(user);
    }

    @Test
    void shouldRetrieveUserPreferences() throws SQLException {
        var user1 = sampleUser();
        userDao.save(user1);

        var user2 = userDao.retrieve(user1.getUserId());

        assertEquals(user1.getPreferences().size(), user2.getPreferences().size());

        for (int i = 0; i < user1.getPreferences().size(); i++) {
            assertEquals(user1.getPreferences().get(i), user2.getPreferences().get(i));
        }
    }

}
