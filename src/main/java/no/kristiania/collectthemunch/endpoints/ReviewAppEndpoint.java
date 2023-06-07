package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.kristiania.collectthemunch.entities.Review;

import java.sql.SQLException;
import java.util.concurrent.Callable;

/**
 Test if using a response works better for exception handling
 200 = OK with json review object.
 404 = notfound with json containing message
 500 = SQL exception with json containing generic internal server error message
*/
@Path("/review/app")
public class ReviewAppEndpoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppReviews(){
        return handleRequest(() -> reviewAppDao.retrieveAllAppReviews();
    }

    @Path("/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppReviewById(@PathParam("userId") int userId){
        return handleRequest(() -> reviewAppDao.retrieveAppReviewById(userId);
    }

    @Path("/getByStars/{numStars}")
    @GET
    @Produces()
    public Response getAppReviewsByStars(@PathParam("numStars") int numStars){
        return handleRequest(() -> reviewAppDao.retrieveAppReviewsByStars(numStars));
    }

    @Path("/{userId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAppReview(Review review, @PathParam("userId") int userId){
        try {
            reviewAppDao.save(review, userId);
        } catch (SQLException e) {
            //TODO: Send a fitting response to frontend.
            //Example:
            /*
            String errorMessage = "An error occurred while saving the review.";
            Response errorResponse = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
            throw new WebApplicationException(errorResponse);
             */
            e.printStackTrace();
        }
    }



}
