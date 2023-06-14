package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.kristiania.collectthemunch.entities.Painting;

import java.sql.SQLException;

@Path("/painting")
public class PaintingEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPainting() {
        return handleRequest(() -> paintingDao.retrieveAllPaintings());
    }

    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Painting painting) {
        return handleSubmit(() -> paintingDao.save(painting));
    }

    @Path("/{paintingId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaintingByPaintingId(@PathParam("paintingId") int paintingId) {
        return handleRequest(() -> paintingDao.retrievePaintingById(paintingId));
    }

    @Path("/name/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaintingByPaintingName(@PathParam("title") String name) {
        return handleRequest(() -> paintingDao.retrievePaintingByName(name));
    }

    @Path("/{userId}/{paintingId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePaintingToInventory(@PathParam("userId") int userId, @PathParam("paintingId") int paintingId) {
        return handleSubmit(() -> paintingDao.saveToInventory(userId, paintingId));
    }
}
