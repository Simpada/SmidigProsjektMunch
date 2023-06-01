package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        userDao.save(user, preferences);
    }

}
