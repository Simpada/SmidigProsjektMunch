package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.kristiania.collectthemunch.entities.Event;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TODO: getEventsByName, getEventsByCategory

@Path("/events")
public class EventEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEvents() {
        try {
            var result = eventDao.retrieveAllEvents();
            return Response.ok(result).build();
        } catch (NotFoundException nfe) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(nfe.getMessage())
                    .build();
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createEvent(Event event) throws SQLException {
        eventDao.save(event);
        eventDao.saveEventCategories(event);
    }


    @Path("/userfilteredevents/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getFilteredEvents(@PathParam("userId") int userId) throws SQLException {
        List<String> tempPreferences = userDao.retrieveUserPreferences(userId);

        return eventDao.filterEvents(tempPreferences);
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
