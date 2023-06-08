package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.kristiania.collectthemunch.database.ItemNotSavedException;
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
        try {
            eventDao.saveEvent(event);
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


    @Path("/userfilteredevents/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getFilteredEvents(@PathParam("userId") int userId) throws SQLException {
        List<String> userPreferences = userDao.retrieveUserPreferences(userId);


        return eventDao.testFilteredEvents(userPreferences);
    }

    @Path("/{eventId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEventById(@PathParam("eventId") int eventId) throws SQLException {
        return eventDao.getEventById(eventId);
    }

    @Path("/category/{category}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getEventsByCategory(@PathParam("category") String category) throws SQLException {

        return eventDao.retrieveEventsByCategory(category);
    }
}
