package no.kristiania.collectthemunch.entities;

import java.util.Arrays;
import java.util.List;

public enum Category {
    EMPTY, PARTY, EXHIBITION, KIDS, FAMILY, NEW, GAMES;


    public static Category parse(String s) {
        s = s.toUpperCase();
        return switch (s) {
            case "EMPTY" -> EMPTY;
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
