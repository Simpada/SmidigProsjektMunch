package no.kristiania.collectthemunch.endpoints;


import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import no.kristiania.collectthemunch.database.*;

import java.sql.SQLException;
import java.util.concurrent.Callable;

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

    protected <T> Response handleRequest(Callable<T> daoCall) {
        try {
            T result = daoCall.call();
            return Response.ok(result).build();
        } catch (NotFoundException nfe) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(nfe.getMessage())
                    .build();
        } catch (SQLException sqlE) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(sqlE.getMessage())
                    .build();
        } catch (Exception e) {
            //TODO: .call() throws Exception, handle that together with SQLExceptions?
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    protected Response handleSubmit(Callable<Void> daoCall) {
        try {
            daoCall.call();
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
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
