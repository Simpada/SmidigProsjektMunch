package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Review;

import java.util.List;

@Path("/review/event")
public class ReviewEventEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getAllEventReviews() {
        return null;
    }

}
