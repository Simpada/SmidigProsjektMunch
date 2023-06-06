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
        return paintingDao.retrieveAll();
    }

    @Path("/{paintingId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Painting retrieve(@PathParam("paintingId") int paintingId) throws SQLException {
        return paintingDao.retrieve(paintingId);
    }

    @Path("/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Painting retrieve(@PathParam("title") String title) throws SQLException {
        return paintingDao.retrieve(title);
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(Painting painting) throws SQLException {
        paintingDao.save(painting);
    }

    @Path("/{userId}/{paintingId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void collectPainting(@PathParam("userId") int userId, @PathParam("paintingId") int paintingId) throws SQLException {
        paintingDao.saveToInventory(userId, paintingId);
    }

}
