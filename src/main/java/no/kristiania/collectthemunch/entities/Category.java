package no.kristiania.collectthemunch.entities;

import java.util.Arrays;
import java.util.List;

public enum Category {
    PARTY, EXHIBITION, KIDS, FAMILY, NEW, GAMES;

    public static List<Category> getAllValues() {
        return Arrays.asList(Category.values());
    }
}
