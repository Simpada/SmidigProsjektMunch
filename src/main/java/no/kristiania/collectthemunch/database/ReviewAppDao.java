package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import no.kristiania.collectthemunch.entities.Review;

import javax.sql.DataSource;

public class ReviewAppDao extends AbstractDao{

    @Inject
    public ReviewAppDao(DataSource dataSource) {
        super(dataSource);
    }

    public Review save() {

    }
}
