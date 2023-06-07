package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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

    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Painting painting) throws SQLException {
        if (paintingDao.save(painting)) {
            return Response.status(201).build();
        } else {
            return Response.status(400).build();
        }
    }

    @Path("/{paintingId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Painting retrieve(@PathParam("paintingId") int paintingId) throws SQLException {
        return paintingDao.retrieve(paintingId);
    }

    @Path("/name/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Painting retrieve(@PathParam("title") String title) throws SQLException {
        return paintingDao.retrieve(title);
    }



    @Path("/{userId}/{paintingId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void collectPainting(@PathParam("userId") int userId, @PathParam("paintingId") int paintingId) throws SQLException {
        paintingDao.saveToInventory(userId, paintingId);
    }

}
