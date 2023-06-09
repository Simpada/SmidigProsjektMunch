package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.kristiania.collectthemunch.database.ItemNotSavedException;
import no.kristiania.collectthemunch.entities.Painting;
import no.kristiania.collectthemunch.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class UserEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return handleRequest(() -> userDao.retrieveAllUsers());
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(User updatedUser) throws SQLException {
        return userDao.updateUser(updatedUser);
    }

    @Path("/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveUserById(@PathParam("userId") int userId) throws SQLException {
        return handleRequest(() -> userDao.retrieveUserById(userId));
    }

    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        try {
            userDao.saveUser(user);
            return Response.status(Response.Status.CREATED).build();
        } catch (ItemNotSavedException insE) {
            insE.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(insE.getMessage())
                    .build();
        } catch (SQLException sqlE) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(sqlE.getMessage())
                    .build();
        }
    }

    @Path("/username/{userName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveUserByUsername(@PathParam("userName") String username) throws SQLException {
        return handleRequest(() -> userDao.retrieveUserByName(username));
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

    @Path("/login/{username}/{password}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User login(@PathParam("username") String username, @PathParam("password") String password) throws SQLException {
        return userDao.login(username, password);
    }
}
