package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Category;
import no.kristiania.collectthemunch.entities.Event;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TODO: getEventsByName, getEventsByCategory

@Path("/events")
public class EventEndPoint extends ApiEndPoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createEvent(Event event) throws SQLException {
        eventDao.save(event);
        eventDao.saveCategoriesByEvent(event);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEvents() {
        //TODO: handle exception better
        List<Event> allEvents = new ArrayList<>();
        try {
            allEvents = eventDao.getAllEventsFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allEvents;
    }
    /*
    //TODO: search for a specific event
    @Path("/event/{eventName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEventByName() {
        return null;
    }
     */

    @Path("/userfilteredevents/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getFilteredEvents(@PathParam("userId") int userId) throws SQLException {
        List<Category> tempPreferences = userDao.retrieveUserPreferences(userId);
        List<String> preferenceCategoryNames = tempPreferences.stream()
                .map(Category::name).toList();
        return eventDao.getFilteredEventsFromDatabase(preferenceCategoryNames);
    }

    @Path("/{eventId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEventById(@PathParam("eventId") int eventId) {
        //TODO: handle exception better
        Event event = new Event();
        try {
            event = eventDao.getEventById(eventId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }


    @Path("/category/{category}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getEventsByCategory(@PathParam("category") String category) {
        //TODO: handle exception better
        List<Event> allEvents = new ArrayList<>();
        try {
            allEvents = eventDao.getEventsByCategoryFromDatabase(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allEvents;
    }
}
