package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Event;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TODO: getEventsByName, getEventsByCategory

@Path("/events")
public class EventEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEvents() {
        //TODO: handle exception better
        List<Event> allEvents = new ArrayList<>();
        try {
            allEvents = eventDao.getAllEvents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allEvents;
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

        return eventDao.getFilteredEvents(tempPreferences);
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

        return eventDao.getEventsByCategory(category);
    }
}
