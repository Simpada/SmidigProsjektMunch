package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.kristiania.collectthemunch.entities.Review;
import java.sql.SQLException;
import java.util.List;

@Path("/review/event")
public class ReviewEventEndpoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEventReviews() {
        return handleRequest(() -> reviewEventDao.retrieveAllEventReviews());
    }

    @Path("/{eventId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReviewsFromEvent(@PathParam("eventId") int eventId) {
        return handleRequest(() -> reviewEventDao.retrieveAllReviewsFromEvent(eventId));
    }

    @Path("/{eventId}/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEventReviews(@PathParam("eventId") int eventId, @PathParam("userId") int userId) {
        return handleRequest(() -> reviewEventDao.retrieveReviewFromUserOnEvent(eventId, userId));
    }

    @Path("/{eventId}/{userId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveReview(Review review, @PathParam("eventId") int eventId, @PathParam("userId") int userId) {
        return handleSubmit(() -> reviewEventDao.save(review, eventId, userId));
    }

    @Path("/user/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReviewsFromUser(@PathParam("userId") int userId) {
        return handleRequest(() -> reviewEventDao.retrieveAllEventReviewsFromUser(userId));
    }
}
