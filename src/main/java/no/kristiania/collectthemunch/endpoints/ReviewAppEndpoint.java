package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.kristiania.collectthemunch.database.ItemNotSavedException;
import no.kristiania.collectthemunch.entities.Review;

import java.sql.SQLException;

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
        return handleRequest(() -> reviewAppDao.retrieveAllAppReviews());
    }

    @Path("/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppReviewById(@PathParam("userId") int userId){
        return handleRequest(() -> reviewAppDao.retrieveAppReviewById(userId));
    }

    @Path("/getByStars/{numStars}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppReviewsByStars(@PathParam("numStars") int numStars){
        return handleRequest(() -> reviewAppDao.retrieveAppReviewsByStars(numStars));
    }

    @Path("/{userId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAppReview(Review review, @PathParam("userId") int userId){
        try {
            reviewAppDao.save(review, userId);
            return Response.status(Response.Status.CREATED).build();
        } catch (ItemNotSavedException insE) {
            insE.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(insE.getMessage())
                    .build();
        } catch (SQLException sqlE) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(sqlE.getMessage())
                .build();
        }
    }
}
