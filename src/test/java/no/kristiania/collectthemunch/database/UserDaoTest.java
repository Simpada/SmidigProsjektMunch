package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static no.kristiania.collectthemunch.SampleData.sampleUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    private final JdbcDataSource dataSource = (JdbcDataSource) MemoryDataSource.createTestDataSource();
    private final UserDao userDao = new UserDao(dataSource);


    @Test
    void shouldSaveAndRetrieveUser() throws SQLException {
        var user = sampleUser();
        userDao.save(user);

        var user2 = userDao.retrieve(user.getUserId());

        System.out.println(user);
        user.printPreferences();


        assertThat(user2)
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(user)
                .isNotSameAs(user);
    }

    @Test
    void shouldRemoveUserPreferences() throws SQLException {
        var user = sampleUser();
        userDao.save(user);

        for (String s : user.getPreferences()) {
            assertNotNull(s);
        }

        userDao.removeUserPreferences(user.getUserId());
        user = userDao.retrieve(user.getUserId());

        assertEquals(user.getPreferences().size(), 0);
    }

    @Test
    void shouldChangeUserPreferences() throws SQLException {
        var user = sampleUser();

        List<String> originalPreferences = Arrays.asList("KIDS", "PARTY");
        user.setPreferences(originalPreferences);
        userDao.save(user);

        List<String> newPreferences = Arrays.asList("NEW", "GAMES");
        user.setPreferences(newPreferences);
        userDao.updatePreferences(user.getUserId(), user.getPreferences());


        user = userDao.retrieve(user.getUserId());


        assertEquals(user.getPreferences().size(), 2);
        assertFalse(originalPreferences.containsAll(user.getPreferences()));
    }

    @Test
    void shouldFailLogin() throws SQLException {
        var user = sampleUser();
        userDao.save(user);

        assertNotNull(userDao.login(user.getUsername(), user.getPassword()));


        user.setUsername("Test");
        user.setPassword("Test");

        userDao.save(user);
        assertNull(userDao.login("NoUser", "WrongPassword"));
    }
}
