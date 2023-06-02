package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Event;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TODO: getAllEvents, getEventsByName, getEventsByCategory

@Path("/events")
public class EventEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getFilteredEvents(@QueryParam("preferences") List<String> preferences) {
        List<Event> filteredEvents = eventDao.getFilteredEventsFromDatabase(preferences);
        return filteredEvents;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEventById(@QueryParam("eventId") int eventId) {
        //TODO: handle exception better
        Event event = new Event();
        try {
            event = eventDao.getEventById(eventId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    //TODO: return a list with all events available in our database
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

    //TODO: search for a specific event
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEventByName() {
        return null;
    }

    //TODO: search for a specific event
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEventByCategory() {
        return null;
    }
}
