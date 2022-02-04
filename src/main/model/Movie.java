package model;

// Represents a movie having a title, a release time,
// an average rating, a user's rating, and a brief intro
public class Movie {
    private String title;
    private int releaseTime;
    private double publicRating;
    private String intro;
    private int userRating;

    // EFFECTS: constructs a movie with title,
    // release time, public rating, intro, and no user
    // rating
    public Movie(String title, int releaseTime,
                 double publicRating, String intro,
                 int userRating) {
        this.title = title;
        this.releaseTime = releaseTime;
        this.publicRating = publicRating;
        this.intro = intro;
        this.userRating = userRating;
    }

    // REQUIRES: rating is an integer in the range of [0,10]
    // MODIFIES: this, ratings
    // EFFECTS: rate the movie and add it to the Ratings list
    public void rateTheMovie(int rating) {
        this.userRating = rating;



    }


}
