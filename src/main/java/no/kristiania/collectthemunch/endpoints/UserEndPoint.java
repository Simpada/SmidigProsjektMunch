package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Category;
import no.kristiania.collectthemunch.entities.User;

import javax.swing.text.IconView;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static no.kristiania.collectthemunch.entities.Category.*;
import static no.kristiania.collectthemunch.entities.Category.GAMES;

@Path("/users")
public class UserEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user, ArrayList<String> preferences) throws SQLException {
        user.setPreferences(parseCategory(preferences));
        userDao.save(user);
    }


    //Parse Category as string from frontend to Category enums.
    public static List<Category> parseCategory(ArrayList<String> preferences) {
        preferences.replaceAll(String::toUpperCase);

        return preferences.stream()
                .map(Category::valueOf).toList();
    }
}
