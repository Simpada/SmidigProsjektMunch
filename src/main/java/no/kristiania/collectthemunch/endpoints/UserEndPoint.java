package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;

@Path("/users")
public class UserEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() throws SQLException {
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user) throws SQLException {
        userDao.save(user);
    }

}
