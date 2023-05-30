package no.kristiania.collectthemunch.endpoints;


import jakarta.inject.Inject;
import no.kristiania.collectthemunch.database.*;

public abstract class ApiEndPoint {

    @Inject
    protected EventDao eventDao;

    @Inject
    protected PaintingDao paintingDao;

    @Inject
    protected ReviewAppDao reviewAppDao;

    @Inject
    protected ReviewEventDao reviewEventDao;

    @Inject
    protected UserDao userDao;

}
