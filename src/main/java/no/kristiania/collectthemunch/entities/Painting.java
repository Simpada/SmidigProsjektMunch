package no.kristiania.collectthemunch.entities;

public class Painting {

    private int paintingId;
    private String name;
    private String author;
    private byte[] paintingImage;
    private String artInformation;
    private String rarity;
    private int points;

    public Painting() {

    }

    public Painting(int paintingId, String name, String author, byte[] paintingImage, String artInformation, String rarity, int points) {
        this.paintingId = paintingId;
        this.name = name;
        this.author = author;
        this.paintingImage = paintingImage;
        this.artInformation = artInformation;
        this.rarity = rarity;
        this.points = points;
    }

    public int getPaintingId() {
        return paintingId;
    }

    public void setPaintingId(int paintingId) {
        this.paintingId = paintingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getPaintingImage() {
        return paintingImage;
    }

    public void setPaintingImage(byte[] paintingImage) {
        this.paintingImage = paintingImage;
    }

    public String getArtInformation() {
        return artInformation;
    }

    public void setArtInformation(String artInformation) {
        this.artInformation = artInformation;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
