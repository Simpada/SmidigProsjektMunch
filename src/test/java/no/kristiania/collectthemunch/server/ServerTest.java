package no.kristiania.collectthemunch.server;

import no.kristiania.collectthemunch.MemoryDataSource;
import no.kristiania.collectthemunch.entities.Category;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ServerTest {

    protected CollectTheMunchServer server;

    @BeforeEach
    void setup() throws Exception {
        server = new CollectTheMunchServer(0, MemoryDataSource.createTestDataSource());
        server.start();
    }

    protected HttpURLConnection getConnection(String path) throws IOException {

        return (HttpURLConnection) new URL(server.getURL(), path).openConnection();

    }

    protected HttpURLConnection postSetup(String path) throws IOException {
        var postMessageConnection = getConnection(path);
        postMessageConnection.setRequestMethod("POST");
        postMessageConnection.setDoOutput(true);
        postMessageConnection.setRequestProperty("Content-Type", "application/json");
        return postMessageConnection;
    }

    protected static void createTestUser(HttpURLConnection postUserConnection, String username) throws IOException {
        String json = "{"+
                    "    \"name\":\""+ username +"\","+
                    "    \"password\":\"MunchMaster\","+
                    "    \"dateOfBirth\":\"10102010\","+
                    "    \"email\":\"munch@munch.munch\","+
                    "    \"preferences\": [\"PARTY\", \"EXHIBITION\"]"+
                    "    \"profilePicture\": \"an image\""+
                    "}";

        postUserConnection.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
    }

    protected static void createTestEvent(HttpURLConnection postEventConnection, String eventName, List<Category> categories) throws IOException {

        String json = "{" +
                "    \"name\":\"" + eventName + "\"," +
                "    \"description\":\"This is an Event\"," +
                "    \"categories\": [";

        for (int i = 0; i < categories.size(); i++) {
            json += "\"" + categories.get(i).name() + "\"";
            if (i < categories.size() - 1) {
                json += ", ";
            }
        }

        json += "]" +
                "}";

        postEventConnection.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
    }

    protected static void createTestReview(HttpURLConnection postReviewConnection, int numOfStars) throws IOException {

        String json = "{" +
                "    \"reviewText\":\"" + "Good Stuff" + "\"," +
                "    \"numOfStars\":" + numOfStars +
                "}";

        postReviewConnection.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));

    }
}
