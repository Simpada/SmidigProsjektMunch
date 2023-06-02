package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.MemoryDataSource;
import no.kristiania.collectthemunch.entities.Category;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static no.kristiania.collectthemunch.SampleData.sampleUser;
import static no.kristiania.collectthemunch.entities.Category.*;
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
        List<Category> originalPref = new ArrayList<>();
        originalPref.add(KIDS);
        originalPref.add(PARTY);
        user.setPreferences(originalPref);

        userDao.save(user);


        List<Category> newPrefs = new ArrayList<>();
        newPrefs.add(NEW);
        newPrefs.add(GAMES);
        user.setPreferences(newPrefs);

        userDao.updatePreferences(user);


        user = userDao.retrieve(user.getUserId());

        for (int i = 0; i < user.getPreferences().size(); i++) {
            System.out.println("Comparing: " + originalPref.get(i).name() + " and " + user.getPreferences().get(i));
            assertNotEquals(originalPref.get(i), user.getPreferences().get(i));

        }

    }
}
