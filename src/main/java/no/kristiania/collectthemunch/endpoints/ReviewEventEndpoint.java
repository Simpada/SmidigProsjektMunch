package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Review;

import java.sql.SQLException;
import java.util.List;

@Path("/review/event")
public class ReviewEventEndpoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getAllEventReviews() throws SQLException {
        return null;
    }

    @Path("/{eventId}/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Review getEventReviews(@PathParam("eventId") int eventId, @PathParam("userId") int userId) throws SQLException {
        return reviewEventDao.getReviewFromUserOnEvent(eventId, userId);
    }
    @Path("/{eventId}/{userId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveReview(Review review, @PathParam("eventId") int eventId, @PathParam("userId") int userId) throws SQLException {
        reviewEventDao.save(review, eventId, userId);
    }


}
