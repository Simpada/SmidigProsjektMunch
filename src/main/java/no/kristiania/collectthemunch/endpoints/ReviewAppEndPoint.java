package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Review;

import java.sql.SQLException;
import java.util.List;

@Path("/review/app")
public class ReviewAppEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getAllAppReviews() throws SQLException {
        return null;
    }

}
