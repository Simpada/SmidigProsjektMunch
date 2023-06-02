package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Collections;

import static no.kristiania.collectthemunch.SampleData.sampleUser;
import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final UserDao userDao = new UserDao(dataSource);


    @Test
    void shouldSaveAndRetrieveUser() throws SQLException {
        var user = sampleUser();
        userDao.save(user);

        var user2 = userDao.retrieve(user.getUserId());

        Collections.sort(user.getPreferences());
        Collections.sort(user2.getPreferences());

        System.out.println(user);
        user.printPreferences();


        assertThat(user2)
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(user)
                .isNotSameAs(user);
    }
}
