package no.kristiania.collectthemunch.endpoints;

import no.kristiania.collectthemunch.server.ServerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ReviewEventEndpointTest extends ServerTest {

    @Test
    void apiShouldPostAndGetEvent() throws IOException {

        var postConnection = postSetup("/api/review/event");

    }


}
