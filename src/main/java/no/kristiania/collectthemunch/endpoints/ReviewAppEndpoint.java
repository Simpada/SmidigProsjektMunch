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
    public Review getAppReviewById(@PathParam("user_id") int userId){

        return null;
    }

    @Path("/getbystars/{num_stars}")
    @GET
    @Produces()
    public List<Review> getAppReviewsByStars(@PathParam("num_stars") int numStars){

        return null;
    }
}
