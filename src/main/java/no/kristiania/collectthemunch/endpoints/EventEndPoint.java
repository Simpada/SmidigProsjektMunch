package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Event;

import java.sql.SQLException;
import java.util.List;

//TODO: getAllEvents, getEventsByName, getEventsByCategory

@Path("/events")
public class EventEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getFilteredEvents(@QueryParam("preferences") List<String> preferences) {
        // Retrieve all events from the database, will at most be an empty list for now
        List<Event> events = null;
        try {
            events = eventDao.getAllEventsFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Event> filteredEvents = null;
        if (events != null){
            // Filter the events based on the provided preferences
            try {
                filteredEvents = eventDao.getEventsByPreferences(preferences, events);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return filteredEvents;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEventById(@QueryParam("eventId") int eventId) {
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
        return null;
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
