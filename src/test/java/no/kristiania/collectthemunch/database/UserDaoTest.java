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
    void shouldSaveAndRetrieveUser() throws SQLException, ItemNotSavedException {
        var user = sampleUser();
        userDao.saveUser(user);

        var user2 = userDao.retrieveUserById(user.getUserId());

        assertThat(user2)
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(user)
                .isNotSameAs(user);
    }

    @Test
    void shouldRemoveUserPreferences() throws SQLException, ItemNotSavedException {
        var user = sampleUser();
        userDao.saveUser(user);

        for (String s : user.getPreferences()) {
            assertNotNull(s);
        }

        userDao.removeUserPreferences(user.getUserId());
        user = userDao.retrieveUserById(user.getUserId());

        assertEquals(user.getPreferences().size(), 0);
    }

    @Test
    void shouldChangeUserPreferences() throws SQLException, ItemNotSavedException {
        var user = sampleUser();

        List<String> originalPreferences = Arrays.asList("KIDS", "PARTY");
        user.setPreferences(originalPreferences);
        userDao.saveUser(user);

        List<String> newPreferences = Arrays.asList("NEW", "GAMES");
        user.setPreferences(newPreferences);
        userDao.updatePreferences(user.getUserId(), user.getPreferences());

        user = userDao.retrieveUserById(user.getUserId());

        assertEquals(user.getPreferences().size(), 2);
        assertFalse(originalPreferences.containsAll(user.getPreferences()));
    }

    @Test
    void shouldFailLogin() throws SQLException, ItemNotSavedException {
        var user = sampleUser();
        userDao.saveUser(user);

        assertNotNull(userDao.login(user.getUsername(), user.getPassword()));

        user.setUsername("Test");
        user.setPassword("Test");

        assertThrows(Exception.class, () -> userDao.saveUser(user));
    }
}
