package no.kristiania.collectthemunch.endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import no.kristiania.collectthemunch.entities.Category;
import no.kristiania.collectthemunch.entities.User;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static no.kristiania.collectthemunch.entities.Category.*;
import static no.kristiania.collectthemunch.entities.Category.GAMES;

@Path("/users")
public class UserEndPoint extends ApiEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() throws SQLException {
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user, ArrayList<String> preferences) throws SQLException {
        user.setPreferences(parseCategory(preferences));
        userDao.save(user);
    }

    //Parse Category as string from frontend to Category enums.
    private static ArrayList<Category> parseCategory(ArrayList<String> preferences) {
        preferences.replaceAll(String::toUpperCase);

        ArrayList<Category> convertedPreferences = new ArrayList<>();
        for (String s : preferences) {
            switch (s) {
                case "PARTY" -> convertedPreferences.add(PARTY);
                case "EXHIBITION" -> convertedPreferences.add(EXHIBITION);
                case "KIDS" -> convertedPreferences.add(KIDS);
                case "FAMILY" -> convertedPreferences.add(FAMILY);
                case "NEW" -> convertedPreferences.add(NEW);
                case "GAMES" -> convertedPreferences.add(GAMES);
            }
        }

        if (preferences.size() == convertedPreferences.size()) {
            System.out.println("Correct size, should be right");
            return convertedPreferences;
        }

        System.out.println("Array did not parse correctly or is null");
        return null;    //Should not happen
    }

}
