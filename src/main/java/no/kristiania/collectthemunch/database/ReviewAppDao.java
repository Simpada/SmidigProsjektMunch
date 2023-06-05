package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import no.kristiania.collectthemunch.entities.Review;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ReviewAppDao extends AbstractDao{

    @Inject
    public ReviewAppDao(DataSource dataSource) {
        super(dataSource);
    }

    public Review save(Review review) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "INSERT INTO App_Reviews (review_text, num_stars) VALUES(?, ?)";
            try(var statement = connection.prepareStatement(query)) {

            }
        }
    }
}
