package no.kristiania.collectthemunch.entities;

public class Review {

    private int id;
    private String reviewText;
    private int numOfStars;

    public Review() {
        this.id = 0;
        this.reviewText = "";
        this.numOfStars = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getNumOfStars() {
        return numOfStars;
    }

    public void setNumOfStars(int numOfStars) {
        this.numOfStars = numOfStars;
    }
}
