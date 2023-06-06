package no.kristiania.collectthemunch;

import no.kristiania.collectthemunch.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleData {
    private static final Random random = new Random();

    public static User sampleUser() {

        var name = (pickOne(
                "Meme",
                "MUNCH",
                "Scream",
                "Ola",
                "Event"
        )) + random.nextInt(100000000);

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
        )) + random.nextInt(10000000)
                + "@munch.com";

        List<String> preferences = new ArrayList<>();
        int preferenceCount = random.nextInt(4) + 1;
        for (int i = 0; i < preferenceCount; i++) {

            int category = random.nextInt(6);

            switch (category) {
                case 0 -> {
                    if (!preferences.contains(String.valueOf(Category.PARTY))) {
                        preferences.add(String.valueOf(Category.PARTY));
                    }
                }
                case 1 -> {
                    if (!preferences.contains(String.valueOf(Category.EXHIBITION))) {
                        preferences.add(String.valueOf(Category.EXHIBITION));
                    }
                }
                case 2 -> {
                    if (!preferences.contains(String.valueOf(Category.KIDS))) {
                        preferences.add(String.valueOf(Category.KIDS));
                    }
                }
                case 3 -> {
                    if (!preferences.contains(String.valueOf(Category.FAMILY))) {
                        preferences.add(String.valueOf(Category.FAMILY));
                    }
                }
                case 4 -> {
                    if (!preferences.contains(String.valueOf(Category.NEW))) {
                        preferences.add(String.valueOf(Category.NEW));
                    }
                }
                case 5 -> {
                    if (!preferences.contains(String.valueOf(Category.GAMES))) {
                        preferences.add(String.valueOf(Category.GAMES));
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

        List<String> categories = new ArrayList<>();
        int categoryCount = random.nextInt(4) + 1;
        for (int i = 0; i < categoryCount; i++) {

            int category = random.nextInt(6);

            switch (category) {
                case 0 -> {
                    if (!categories.contains(String.valueOf(Category.PARTY))) {
                        categories.add(String.valueOf(Category.PARTY));
                    }
                }
                case 1 -> {
                    if (!categories.contains(String.valueOf(Category.EXHIBITION))) {
                        categories.add(String.valueOf(Category.EXHIBITION));
                    }
                }
                case 2 -> {
                    if (!categories.contains(String.valueOf(Category.KIDS))) {
                        categories.add(String.valueOf(Category.KIDS));
                    }
                }
                case 3 -> {
                    if (!categories.contains(String.valueOf(Category.FAMILY))) {
                        categories.add(String.valueOf(Category.FAMILY));
                    }
                }
                case 4 -> {
                    if (!categories.contains(String.valueOf(Category.NEW))) {
                        categories.add(String.valueOf(Category.NEW));
                    }
                }
                case 5 -> {
                    if (!categories.contains(String.valueOf(Category.GAMES))) {
                        categories.add(String.valueOf(Category.GAMES));
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

        var name = (pickOne(
                "Scream",
                "Oh noo",
                "Banana",
                "Masterchef"
        )) + random.nextInt(1000);

        var author = (pickOne(
                "Edvard Munch",
                "Vincent Van Gogh",
                "Darude",
                "Claude Monet"
        ));

        var artInformation = (pickOne(
                "Vibrant swirls evoke introspection, capturing the essence of human emotion",
                "Expressive brushstrokes reveal haunting figures amidst a landscape of melancholy",
                "Bold colors collide, conveying the intensity of raw passion and inner turmoil",
                "Ethereal forms emerge, blurring the line between reality and the subconscious"
        ));

        var rarity = (pickOne(
                "COMMON",
                "RARE",
                "EPIC",
                "LEGENDARY"
        ));

        var points = (pickOne(
                100,
                250,
                500,
                1000
        ));

        painting.setName(name);
        painting.setAuthor(author);
        painting.setArtInformation(artInformation);
        painting.setRarity(rarity);
        painting.setPoints(points);

        return painting;
    }

    private static String pickOne(String... alternatives) {
        return alternatives[random.nextInt(alternatives.length)];
    }

    private static int pickOne(int... alternatives) {
        return alternatives[random.nextInt(alternatives.length)];
    }

}
