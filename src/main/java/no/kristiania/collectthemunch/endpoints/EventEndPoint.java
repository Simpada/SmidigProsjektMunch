package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.kristiania.collectthemunch.entities.Event;

import java.sql.SQLException;
import java.util.List;

@Path("/events")
public class EventEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEvents() {
        return handleRequest(() -> eventDao.retrieveAllEvents());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEvent(Event event) {
        return handleSubmit(() -> eventDao.saveEvent(event));
    }

    @Path("/{eventId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEventById(@PathParam("eventId") int eventId) {
        return handleRequest(() -> eventDao.retrieveEventById(eventId));
    }

    @Path("/eventsByName/{userSearch}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEventsByName(@PathParam("userSearch") String userSearch) {
        return handleRequest(() -> eventDao.retrieveEventsByName(userSearch));
    }

    @Path("/userFilteredEvents/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilteredEvents(@PathParam("userId") int userId) throws SQLException {
        List<String> userPreferences = userDao.retrieveUserPreferences(userId);
        return handleRequest(() -> eventDao.retrieveUserSpecificEvents(userPreferences));
    }

    @Path("/category/{category}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEventsByCategory(@PathParam("category") String category) {
        return handleRequest(() -> eventDao.retrieveEventsByCategory(category));
    }
}
