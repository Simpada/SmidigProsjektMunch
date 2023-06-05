package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Painting;

import javax.print.attribute.standard.Media;
import java.sql.SQLException;
import java.util.List;

@Path("/painting")
public class PaintingEndPoint extends ApiEndPoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(Painting painting) throws SQLException {
        paintingDao.save(painting);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Painting> getAllPainting() throws SQLException {
        return paintingDao.retrieveAll();
    }

    @Path("/inventory")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Painting> getPaintingForUser(int userId) throws SQLException {
        return paintingDao.retrieveAllForUser(userId);
    }

    @Path("/{paintingId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Painting getPainting(@PathParam("paintingId") int paintingId) {
        return null;
    }

}
