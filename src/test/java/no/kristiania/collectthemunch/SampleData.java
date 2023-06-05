package no.kristiania.collectthemunch;


import no.kristiania.collectthemunch.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleData {
    private static final Random random = new Random();

    public static User sampleUser() {

        var name = (pickOne(
                "Meme_guy",
                "MUNCHmaster",
                "Screamy",
                "OlaNordmann",
                "EventGoer"
        )) + random.nextInt(1000000);

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
        ))  + random.nextInt(10000000)
            + "@munch.com";

        List<Category> preferences = new ArrayList<>();
        int preferenceCount = random.nextInt(4) + 1;
        for (int i = 0; i < preferenceCount; i++) {

            int category = random.nextInt(6);

            switch (category) {
                case 0 -> {
                    if (!preferences.contains(Category.PARTY)) {
                        preferences.add(Category.PARTY);
                    }
                }
                case 1 -> {
                    if (!preferences.contains(Category.EXHIBITION)) {
                        preferences.add(Category.EXHIBITION);
                    }
                }
                case 2 -> {
                    if (!preferences.contains(Category.KIDS)) {
                        preferences.add(Category.KIDS);
                    }
                }
                case 3 -> {
                    if (!preferences.contains(Category.FAMILY)) {
                        preferences.add(Category.FAMILY);
                    }
                }
                case 4 -> {
                    if (!preferences.contains(Category.NEW)) {
                        preferences.add(Category.NEW);
                    }
                }
                case 5 -> {
                    if (!preferences.contains(Category.GAMES)) {
                        preferences.add(Category.GAMES);
                    }
                }
            }
        }

        var user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.setDateOfBirth(dateOfBirth);
        user.setEmail(email);
        user.setPreferences(preferences);

        return user;
    }

    public static Event sampleEvent() {

        var name = (pickOne(
                "Cool Guy Event",
                "Painting Hunt",
                "Finger Paint Competition",
                "Munch Freestyle rap",
                "Party Time"
        )) + random.nextInt(10000000);

        var description = (pickOne(
                "Fun event at MUNCH",
                "Win great rewards",
                "Have the time of your life",
                "Bring all your friends",
                "Express yourself",
                "Create great memories"
        ));

        List<Category> categories = new ArrayList<>();
        int categoryCount = random.nextInt(4) + 1;
        for (int i = 0; i < categoryCount; i++) {

            int category = random.nextInt(6);

            switch (category) {
                case 0 -> {
                    if (!categories.contains(Category.PARTY)) {
                        categories.add(Category.PARTY);
                    }
                }
                case 1 -> {
                    if (!categories.contains(Category.EXHIBITION)) {
                        categories.add(Category.EXHIBITION);
                    }
                }
                case 2 -> {
                    if (!categories.contains(Category.KIDS)) {
                        categories.add(Category.KIDS);
                    }
                }
                case 3 -> {
                    if (!categories.contains(Category.FAMILY)) {
                        categories.add(Category.FAMILY);
                    }
                }
                case 4 -> {
                    if (!categories.contains(Category.NEW)) {
                        categories.add(Category.NEW);
                    }
                }
                case 5 -> {
                    if (!categories.contains(Category.GAMES)) {
                        categories.add(Category.GAMES);
                    }
                }
            }
        }

        var event = new Event();

        event.setName(name);
        event.setDescription(description);
        event.setCategories(categories);

        return event;
    }

    public static Review sampleReview() {

        var reviewText = (pickOne(
                "Had the best time",
                "Not enough modern art",
                "The staff were great",
                "Love it",
                "Fun",
                "Woof woof bark bark"
        ));

        var numOfStars = random.nextInt(5) + 1;

        var review = new Review();

        review.setReviewText(reviewText);
        review.setNumOfStars(numOfStars);

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
