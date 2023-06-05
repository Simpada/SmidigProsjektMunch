package no.kristiania.collectthemunch.entities;

public enum Rarity {
    COMMON, RARE, EPIC, LEGENDARY;




    public static String parseToString(Rarity rarityAsEnum) {
        return String.valueOf(rarityAsEnum);
    }
}

