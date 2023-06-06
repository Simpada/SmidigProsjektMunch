package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Painting;
import no.kristiania.collectthemunch.entities.User;

import javax.print.attribute.standard.Media;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class UserEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() throws SQLException {
        return userDao.retrieveAll();
    }

    @Path("/login/{username}/{password}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User login(@PathParam("username") String username, @PathParam("password") String password) throws SQLException {
        return userDao.login(username, password);
    }

    @Path("/register")
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

    @Path("/username/{userName}")
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

    @Path("/inventory/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Painting> getPaintingsForUser(@PathParam("userId") int userId) throws SQLException {
        return paintingDao.retrieveAllForUser(userId);
    }

}
