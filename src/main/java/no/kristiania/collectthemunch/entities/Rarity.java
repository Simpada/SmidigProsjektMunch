package no.kristiania.collectthemunch.entities;

public enum Rarity {
    COMMON, RARE, EPIC, LEGENDARY;

    public static Rarity parseToEnum(String s) {
        s = s.toUpperCase();
        return switch (s) {
            case "COMMON" -> COMMON;
            case "RARE" -> RARE;
            case "EPIC" -> EPIC;
            case "LEGENDARY" -> LEGENDARY;
            default -> null;
        };
    }

    public static String parseToString(Rarity rarityAsEnum) {
        return String.valueOf(rarityAsEnum);
    }
}

