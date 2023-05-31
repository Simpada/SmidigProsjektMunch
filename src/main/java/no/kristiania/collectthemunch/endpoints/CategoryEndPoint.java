package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.kristiania.collectthemunch.entities.Category;

import java.util.List;

@Path("/categories")
public class CategoryEndPoint extends ApiEndPoint{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories() {
        List<Category> categories = Category.getAllValues();
        return Response.ok(categories).build();
    }
}
