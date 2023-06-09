package no.kristiania.collectthemunch.database;

public class ItemNotSavedException extends Exception{
    public ItemNotSavedException(String message) {
        super(message);
    }
}
