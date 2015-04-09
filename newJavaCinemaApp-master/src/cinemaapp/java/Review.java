package cinemaapp.java;

public class Review {
    //attributes
    private int reviewID;
    private int movieID;
    private int rating;
    private String reviewContent;
    //test
    public Review (int i, int mID, int r, String rc){//paramatized constructor
        this.reviewID = i;
        this.movieID = mID;
        this.rating = r;
        this.reviewContent = rc;
    }
    
    public Review (int mID, int r, String rc){
        this(-1, mID, r, rc);//my genreID is auto increment, the user cannot edit genreID or give it a value
    }
    //my get methods
    public int getReviewId() {
        return reviewID;
    }
    public int getMovieId() {
        return movieID;
    }
    public int getRating() {
        return rating;
    }
    public String getReviewContent() {
        return reviewContent;
    }
    //my set methods
    public void setId(int reviewID) {
        this.reviewID = reviewID;
    }
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }
}
