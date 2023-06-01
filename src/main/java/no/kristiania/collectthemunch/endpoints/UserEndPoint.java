package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Category;
import no.kristiania.collectthemunch.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Path("/users")
public class UserEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() throws SQLException {
        return userDao.retrieveAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user, ArrayList<String> preferences) throws SQLException {
        user.setPreferences(parseCategory(preferences));
        userDao.save(user);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUserPreferences(User user) throws SQLException {
        userDao.updatePreferences(user);
    }


    //Parse Category as string from frontend to Category enums.
    public static List<Category> parseCategory(ArrayList<String> preferences) {
        preferences.replaceAll(String::toUpperCase);

        return preferences.stream()
                .map(Category::valueOf).toList();
    }
}
