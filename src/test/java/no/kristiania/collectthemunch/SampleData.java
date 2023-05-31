package no.kristiania.collectthemunch;


import no.kristiania.collectthemunch.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleData {
    private static final Random random = new Random();

    public static User sampleUser() {

        var name = (pickOne(
                "Meme_guy_420",
                "MUNCHmaster",
                "Screamy",
                "OlaNordmann",
                "EventGoer"
        )) + random.nextInt(10000000);

        var password = (pickOne(
                "1234",
                "password",
                "Secret",
                "shhhhhhh",
                "Mommy_lover",
                "Actually_prefer_gogh"
        ));

        var dateOfBirth = (pickOne(
                "10102010",
                "12122012",
                "14142014",
                "16162016",
                "18182018",
                "20202020"
        ));

        var email = (pickOne(
                "scream",
                "KariNordmann",
                "thisIsAnEmail",
                "Brrrgrrr",
                "ILikeMunch",
                "fdawdngoe"
        )) + "@munch.com";


        // Also need to randomize preferences
        List<Category> preferences = new ArrayList<>();

        var user = new User();
        user.setUserName(name);
        user.setPassword(password);
        user.setDateOfBirth(dateOfBirth);
        user.setEmail(email);
        user.setPreferences(preferences);

        return user;
    }

    public static Event sampleEvent() {

        var event = new Event();

        return event;
    }

    public static Review sampleReview() {

        var review = new Review();

        return review;
    }

    public static Painting samplePainting() {

        var painting = new Painting();

        return painting;
    }


    private static String pickOne(String... alternatives) {
        return alternatives[random.nextInt(alternatives.length)];
    }


}
