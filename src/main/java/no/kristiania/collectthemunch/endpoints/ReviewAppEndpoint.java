package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.kristiania.collectthemunch.entities.Review;

import java.sql.SQLException;
import java.util.List;

@Path("/review/app")
public class ReviewAppEndpoint extends ApiEndPoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAppReview(Review review){
        try {
            reviewAppDao.save(review);
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getAllAppReviews() throws SQLException {

        return null;
    }

    @Path("/getbyId/{user_id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppReviewById(@PathParam("user_id") int userId){
        //TODO: Test if using a response works better for exception handling
        // 200 = OK with json review object.
        // 404 = notfound with json containing message
        // 500 = SQL exception with json containing generic internal server error message
        // frontend has to account for other response codes than 200.
        try {
            Review review = reviewAppDao.retrieveReviewById(userId);
            return Response.ok(review).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception
            return Response.serverError().build();
        }
        /*
        var review = new Review();
        try {
            review = reviewAppDao.retrieveReviewById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return review;
        */
    }

    @Path("/getbystars/{num_stars}")
    @GET
    @Produces()
    public List<Review> getAppReviewsByStars(@PathParam("num_stars") int numStars){

        return null;
    }
}
