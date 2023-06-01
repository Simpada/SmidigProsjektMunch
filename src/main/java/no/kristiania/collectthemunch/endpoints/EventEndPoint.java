package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Event;

import java.sql.SQLException;
import java.util.List;

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


}
