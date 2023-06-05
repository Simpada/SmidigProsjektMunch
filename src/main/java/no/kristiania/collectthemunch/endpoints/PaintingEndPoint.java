package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Painting;

import java.sql.SQLException;
import java.util.List;

@Path("/painting")
public class PaintingEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Painting> getAllPainting() throws SQLException {
        return null;
    }

    @Path("/{paintingId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Painting getPainting(@PathParam("paintingId") int paintingId) {
        return null;
    }

}
