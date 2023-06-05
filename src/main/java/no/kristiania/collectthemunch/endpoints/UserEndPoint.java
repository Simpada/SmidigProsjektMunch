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
        return userDao.retrieveAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user) throws SQLException {
        userDao.save(user);
    }

    @Path("/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User retrieveUserById(@PathParam("userId") int userId) throws SQLException {
        return userDao.retrieve(userId);
    }

    @Path("/name/{userName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User retrieveUserByUsername(@PathParam("userName") String username) throws SQLException {
        return userDao.retrieve(username);
    }


    @Path("/{userId}/preferences")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUserPreferences(@PathParam("userId") int userId, ArrayList<String> preferences) throws SQLException {
        userDao.updatePreferences(userId, preferences);
    }

}
