package no.kristiania.collectthemunch;

import jakarta.inject.Singleton;
import no.kristiania.collectthemunch.database.*;
import no.kristiania.collectthemunch.endpoints.ReviewEndPoint;
import org.glassfish.jersey.internal.inject.AbstractBinder;

import javax.sql.DataSource;

public class ResourceConfig extends org.glassfish.jersey.server.ResourceConfig {

    //DataSource as parameter to constructor to be able to variable datasource (For prod / testing)
    public ResourceConfig(DataSource dataSource) {
        super(ReviewEndPoint.class);

        register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(UserDao.class)
                        .to(UserDao.class)
                        .in(Singleton.class);
                bind(dataSource)
                        .to(DataSource.class)
                        // Not sure if this is right, might not need it
                        .in(Singleton.class);
            }
        });
    }
}