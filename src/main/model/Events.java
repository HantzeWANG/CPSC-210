package model;

// Represents a movie having a title, a release time,
// an average rating, a user's rating, and a brief intro
public class Events {
    private String title;
    private int releaseTime;
    private String intro;
    private int userRating;

    // EFFECTS: constructs a movie with title,
    // release time, public rating, intro, and no user
    // rating
    public Events(String title, int releaseTime,
                  double publicRating, String intro,
                  int userRating) {
        this.title = title;
        this.releaseTime = releaseTime;
        this.intro = intro;
        this.userRating = userRating;
    }

    // REQUIRES: rating is an integer in the range of [0,10]
    // MODIFIES: this, ratings
    // EFFECTS: rate the movie and add it to the Ratings list
    public void rateTheMovie(int rating) {
        this.userRating = rating;

    }

    // getters
    public String getTitle() {
        return title;
    }

    public int getReleaseTime() {
        return releaseTime;
    }

    public String getIntro() {
        return intro;
    }

    public int getUserRating() {
        return userRating;
    }

    // EFFECTS: returns a String representation of movie
    public String toString() {
        return "title: " + getTitle() + "," + "release time: " + getReleaseTime()
                + "intro: " + getIntro();
    }


}
