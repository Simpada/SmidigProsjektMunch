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
    public List<Event> getAllEvents() throws SQLException {
        return null;
    }


}
