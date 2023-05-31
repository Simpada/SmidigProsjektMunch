package no.kristiania.collectthemunch;


import no.kristiania.collectthemunch.entities.Event;
import no.kristiania.collectthemunch.entities.Painting;
import no.kristiania.collectthemunch.entities.Review;
import no.kristiania.collectthemunch.entities.User;

import java.util.Random;

public class SampleData {
    private static final Random random = new Random();

    public static User sampleUser() {


        var user = new User();

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
