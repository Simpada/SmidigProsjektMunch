package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import no.kristiania.collectthemunch.entities.Category;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
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

    @Test
    void shouldRemoveUserPreferences() throws SQLException {
        var user = sampleUser();
        userDao.save(user);

        for (Category c : user.getPreferences()) {
            assertNotNull(c);
        }

        userDao.removeUserPreferences(user.getUserId());
        user = userDao.retrieve(user.getUserId());

        assertEquals(user.getPreferences().size(), 0);
    }

    @Test
    void shouldChangeUserPreferences() throws SQLException {
        var user = sampleUser();

        List<Category> originalPreferences = Arrays.asList(Category.KIDS, Category.PARTY);
        user.setPreferences(originalPreferences);
        userDao.save(user);

        List<Category> newPreferences = Arrays.asList(Category.NEW, Category.GAMES);
        user.setPreferences(newPreferences);
        userDao.updatePreferences(user.getUserId(), user.getPreferences());


        user = userDao.retrieve(user.getUserId());


        assertEquals(user.getPreferences().size(), 2);
        assertFalse(originalPreferences.containsAll(user.getPreferences()));
    }
}
