package no.kristiania.collectthemunch.entities;

public enum Rarity {
    COMMON, RARE, EPIC, LEGENDARY;


    public static boolean validateRarityEnum(String rarity) {
        for (Rarity r : Rarity.values()) {
            if (r.name().equals(rarity.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

}

