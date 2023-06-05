package no.kristiania.collectthemunch.entities;

public class Painting {

    private int paintingId;
    private String title;
    private String author;
    private byte[] painting_image;
    private String art_information;
    private Rarity rarity;
    private int points;

    public Painting() {

    }

    public Painting(int paintingId, String title, String author, byte[] painting_image, String art_information, Rarity rarity, int points) {
        this.paintingId = paintingId;
        this.title = title;
        this.author = author;
        this.painting_image = painting_image;
        this.art_information = art_information;
        this.rarity = rarity;
        this.points = points;
    }

    public int getPaintingId() {
        return paintingId;
    }

    public void setPaintingId(int paintingId) {
        this.paintingId = paintingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getPainting_image() {
        return painting_image;
    }

    public void setPainting_image(byte[] painting_image) {
        this.painting_image = painting_image;
    }

    public String getArt_information() {
        return art_information;
    }

    public void setArt_information(String art_information) {
        this.art_information = art_information;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
