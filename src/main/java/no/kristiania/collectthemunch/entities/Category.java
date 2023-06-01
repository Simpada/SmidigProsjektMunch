package no.kristiania.collectthemunch.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Category {
    PARTY, EXHIBITION, KIDS, FAMILY, NEW, GAMES;

    //Parse Category as string from frontend to Category enums.
    public static ArrayList<Category> parseCategory(ArrayList<String> preferences) {
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

    public static Category parse(String s) {
        s = s.toUpperCase();
        return switch (s) {
            case "PARTY" -> PARTY;
            case "EXHIBITION" -> EXHIBITION;
            case "KIDS" -> KIDS;
            case "FAMILY" -> FAMILY;
            case "NEW" -> NEW;
            case "GAMES" -> GAMES;
            default -> null;
        };
    }

    public static List<Category> getAllValues() {
        return Arrays.asList(Category.values());
    }
}
