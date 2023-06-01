package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Category;
import no.kristiania.collectthemunch.entities.User;

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
    public List<User> getAllUsers() throws SQLException {
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user, ArrayList<String> preferences) throws SQLException {
        user.setPreferences(parseCategory(preferences));
        userDao.save(user);
    }



}
